package com.wheelshare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wheelshare.client.dto.JoinRideRequestDto;
import com.wheelshare.client.dto.SharedRideRequestDto;
import com.wheelshare.entity.RideShareRequest;
import com.wheelshare.service.RideShareService;

import com.wheelshare.entity.Ride;

@RestController
@RequestMapping("/api/rideshare")
public class RideShareController {

    @Autowired
    private RideShareService rideShareService;

    // Create a shared ride
    @PostMapping("/create/{userId}")
    public ResponseEntity<Ride> createSharedRide(@PathVariable Long userId,
            @RequestBody SharedRideRequestDto requestDto) {
        try {
            Ride ride = rideShareService.createSharedRide(userId, requestDto);
            return ResponseEntity.ok(ride);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Find available shared rides near a location
    @GetMapping("/find")
    public ResponseEntity<List<Ride>> findShareableRides(
            @RequestParam Double pickupLat,
            @RequestParam Double pickupLng,
            @RequestParam(defaultValue = "5.0") Double radiusKm) {

        List<Ride> rides = rideShareService.findShareableRides(pickupLat, pickupLng, radiusKm);
        return ResponseEntity.ok(rides);
    }

    // Request to join a shared ride
    @PostMapping("/join/{userId}")
    public ResponseEntity<RideShareRequest> requestToJoinRide(@PathVariable Long userId,
            @RequestBody JoinRideRequestDto requestDto) {
        try {
            RideShareRequest request = rideShareService.requestToJoinRide(userId, requestDto);
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Accept a ride share request (by ride owner)
    @PutMapping("/accept/{requestId}/{rideOwnerId}")
    public ResponseEntity<RideShareRequest> acceptRideShareRequest(@PathVariable Long requestId,
            @PathVariable Long rideOwnerId) {
        try {
            RideShareRequest request = rideShareService.acceptRideShareRequest(requestId, rideOwnerId);
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Reject a ride share request (by ride owner)
    @PutMapping("/reject/{requestId}/{rideOwnerId}")
    public ResponseEntity<RideShareRequest> rejectRideShareRequest(@PathVariable Long requestId,
            @PathVariable Long rideOwnerId) {
        try {
            RideShareRequest request = rideShareService.rejectRideShareRequest(requestId, rideOwnerId);
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get pending requests for a ride (for ride owner)
    @GetMapping("/requests/ride/{rideId}")
    public ResponseEntity<List<RideShareRequest>> getPendingRequestsForRide(@PathVariable Long rideId) {
        List<RideShareRequest> requests = rideShareService.getPendingRequestsForRide(rideId);
        return ResponseEntity.ok(requests);
    }

    // Get all requests made by a user
    @GetMapping("/requests/user/{userId}")
    public ResponseEntity<List<RideShareRequest>> getUserRideShareRequests(@PathVariable Long userId) {
        List<RideShareRequest> requests = rideShareService.getUserRideShareRequests(userId);
        return ResponseEntity.ok(requests);
    }

    // Calculate fare for shared ride
    @GetMapping("/fare/{rideId}")
    public ResponseEntity<Double> calculateSharedRideFare(@PathVariable Long rideId,
            @RequestParam Double pickupLat,
            @RequestParam Double pickupLng,
            @RequestParam Double dropLat,
            @RequestParam Double dropLng) {
        try {
            Double fare = rideShareService.calculateSharedRideFare(rideId, pickupLat, pickupLng, dropLat, dropLng);
            return ResponseEntity.ok(fare);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
