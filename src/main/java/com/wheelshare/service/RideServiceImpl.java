package com.wheelshare.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.wheelshare.client.dto.RideRequestDto;
import com.wheelshare.client.enums.RideStatus;
import com.wheelshare.entity.Ride;
import com.wheelshare.entity.User;
import com.wheelshare.repository.RideRepository;
import com.wheelshare.repository.UserRepository;
import com.wheelshare.util.DistanceCalculator;

@Service 
public class RideServiceImpl implements RideService {
	private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final FareService fareService;

    public RideServiceImpl(RideRepository rideRepository,
                       UserRepository userRepository,
                       FareService fareService) {
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
        this.fareService = fareService;
    }

    public Ride requestRide(RideRequestDto dto) {

        // 1. Fetch customer
        User customer = userRepository.findById(dto.getCustomerId().intValue())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 2. Calculate distance
        double distanceKm = DistanceCalculator.calculateDistanceKm(
                dto.getSourceLat(),
                dto.getSourceLng(),
                dto.getDestinationLat(),
                dto.getDestinationLng()
        );

        // 3. Calculate fare
        double fare = fareService.calculateFare(distanceKm);

        // 4. Create ride
        Ride ride = Ride.builder()
                .rideOwner(customer)
                .sourceLat(dto.getSourceLat())
                .sourceLng(dto.getSourceLng())
                .destinationLat(dto.getDestinationLat())
                .destinationLng(dto.getDestinationLng())
                .distanceKm(distanceKm)
                .totalFare(fare)
                .status(RideStatus.REQUESTED)
                .requestedAt(LocalDateTime.now())
                .build();

        // 5. Save ride
        return rideRepository.save(ride);
    }


}
