package com.wheelshare.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wheelshare.client.dto.JoinRideRequestDto;
import com.wheelshare.client.dto.SharedRideRequestDto;
import com.wheelshare.client.enums.RideShareRequestStatus;
import com.wheelshare.client.enums.RideStatus;
import com.wheelshare.client.enums.RideType;
import com.wheelshare.entity.Ride;
import com.wheelshare.entity.RidePassenger;
import com.wheelshare.entity.RideShareRequest;
import com.wheelshare.entity.User;
import com.wheelshare.repository.RidePassengerRepository;
import com.wheelshare.repository.RideRepository;
import com.wheelshare.repository.RideShareRequestRepository;
import com.wheelshare.repository.UserRepository;
import com.wheelshare.util.DistanceCalculator;


@Service
public class RideShareServiceImpl implements RideShareService {

	@Autowired
    private RideRepository rideRepository;

    @Autowired
    private RideShareRequestRepository rideShareRequestRepository;

    @Autowired
    private RidePassengerRepository ridePassengerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FareService fareService;

    @Override
    public Ride createSharedRide(Long userId, SharedRideRequestDto requestDto) {
        User user = userRepository.findById(userId.intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create the ride
        Ride ride = Ride.builder()
                .rideOwner(user)
                .sourceLat(requestDto.getPickupLat())
                .sourceLng(requestDto.getPickupLng())
                .sourceAddress(requestDto.getPickupAddress())
                .destinationLat(requestDto.getDropLat())
                .destinationLng(requestDto.getDropLng())
                .destinationAddress(requestDto.getDropAddress())
                .rideType(RideType.valueOf(requestDto.getRideType()))
                .maxPassengers(requestDto.getMaxPassengers())
                .currentPassengers(1)
                .isShareable(requestDto.getIsShareable())
                .status(RideStatus.REQUESTED)
                .requestedAt(LocalDateTime.now())
                .build();

        // Calculate distance and fare
        double distance = DistanceCalculator.calculateDistance(
                requestDto.getPickupLat(), requestDto.getPickupLng(),
                requestDto.getDropLat(), requestDto.getDropLng());

        ride.setDistanceKm(distance);
        ride.setTotalFare(fareService.calculateFare(distance));

        Ride savedRide = rideRepository.save(ride);

        // Add ride owner as first passenger
        RidePassenger ownerPassenger = RidePassenger.builder()
                .ride(savedRide)
                .passenger(user)
                .pickupLat(requestDto.getPickupLat())
                .pickupLng(requestDto.getPickupLng())
                .pickupAddress(requestDto.getPickupAddress())
                .dropLat(requestDto.getDropLat())
                .dropLng(requestDto.getDropLng())
                .dropAddress(requestDto.getDropAddress())
                .individualFare(savedRide.getTotalFare()) // Initially full fare
                .joinedAt(LocalDateTime.now())
                .build();

        ridePassengerRepository.save(ownerPassenger);

        return savedRide;
    }

    @Override
    public List<Ride> findShareableRides(Double pickupLat, Double pickupLng, Double radiusKm) {
        return rideRepository.findShareableRidesNearby(pickupLat, pickupLng, radiusKm);
    }

    @Override
    public RideShareRequest requestToJoinRide(Long userId, JoinRideRequestDto requestDto) {
        User user = userRepository.findById(userId.intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Ride ride = rideRepository.findById(requestDto.getRideId())
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        // Check if ride is still available for sharing
        if (!ride.getIsShareable() || ride.getCurrentPassengers() >= ride.getMaxPassengers()) {
            throw new RuntimeException("Ride is not available for sharing");
        }

        // Calculate estimated fare for this passenger
        Double estimatedFare = calculateSharedRideFare(ride.getId(),
                requestDto.getPickupLat(), requestDto.getPickupLng(),
                requestDto.getDropLat(), requestDto.getDropLng());

        RideShareRequest shareRequest = RideShareRequest.builder()
                .ride(ride)
                .requester(user)
                .pickupLat(requestDto.getPickupLat())
                .pickupLng(requestDto.getPickupLng())
                .pickupAddress(requestDto.getPickupAddress())
                .dropLat(requestDto.getDropLat())
                .dropLng(requestDto.getDropLng())
                .dropAddress(requestDto.getDropAddress())
                .estimatedFare(estimatedFare)
                .message(requestDto.getMessage())
                .requestedAt(LocalDateTime.now())
                .status(RideShareRequestStatus.PENDING)
                .build();
                
        return rideShareRequestRepository.save(shareRequest);
    }

    @Override
    public RideShareRequest acceptRideShareRequest(Long requestId, Long rideOwnerId) {
        RideShareRequest request = rideShareRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        Ride ride = request.getRide();

        // Verify the user is the ride owner
        if (!ride.getRideOwner().getId().equals(rideOwnerId)) {
            throw new RuntimeException("Only ride owner can accept requests");
        }

        // Check if ride still has capacity
        if (ride.getCurrentPassengers() >= ride.getMaxPassengers()) {
            throw new RuntimeException("Ride is full");
        }

        // Add passenger to ride
        RidePassenger newPassenger = RidePassenger.builder()
                .ride(ride)
                .passenger(request.getRequester())
                .pickupLat(request.getPickupLat())
                .pickupLng(request.getPickupLng())
                .pickupAddress(request.getPickupAddress())
                .dropLat(request.getDropLat())
                .dropLng(request.getDropLng())
                .dropAddress(request.getDropAddress())
                .individualFare(request.getEstimatedFare())
                .joinedAt(LocalDateTime.now())
                .build();

        ridePassengerRepository.save(newPassenger);

        // Update ride passenger count
        ride.setCurrentPassengers(ride.getCurrentPassengers() + 1);
        rideRepository.save(ride);

        // Update request status
        request.setRespondedAt(LocalDateTime.now());

        request.setStatus(RideShareRequestStatus.ACCEPTED);
        return rideShareRequestRepository.save(request);
    }

    @Override
    public RideShareRequest rejectRideShareRequest(Long requestId, Long rideOwnerId) {
        RideShareRequest request = rideShareRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // Verify the user is the ride owner
        if (!request.getRide().getRideOwner().getId().equals(rideOwnerId)) {
            throw new RuntimeException("Only ride owner can reject requests");
        }

        request.setRespondedAt(LocalDateTime.now());

        request.setStatus(RideShareRequestStatus.REJECTED);
        return rideShareRequestRepository.save(request);
    }

    @Override
    public List<RideShareRequest> getPendingRequestsForRide(Long rideId) {
        return rideShareRequestRepository.findPendingRequestsByRideId(rideId);
    }

    @Override
    public List<RideShareRequest> getUserRideShareRequests(Long userId) {
        return rideShareRequestRepository.findRequestsByUserId(userId);
    }

    @Override
    public Double calculateSharedRideFare(Long rideId, Double pickupLat, Double pickupLng,
            Double dropLat, Double dropLng) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        // Calculate distance for this passenger's route
        double passengerDistance = DistanceCalculator.calculateDistance(
                pickupLat, pickupLng, dropLat, dropLng);

        // Base fare calculation
        double baseFare = fareService.calculateFare(passengerDistance);

        // Apply sharing discount (e.g., 20% discount for shared rides)
        double sharingDiscount = 0.20;
        double sharedFare = baseFare * (1 - sharingDiscount);

        return Math.round(sharedFare * 100.0) / 100.0; // Round to 2 decimal places
    }

}
