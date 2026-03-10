package com.wheelshare.service;

import java.util.List;

import com.wheelshare.client.dto.JoinRideRequestDto;
import com.wheelshare.client.dto.SharedRideRequestDto;
import com.wheelshare.entity.Ride;
import com.wheelshare.entity.RideShareRequest;

public interface RideShareService {
	// Create a shared ride
    Ride createSharedRide(Long userId, SharedRideRequestDto requestDto);
    
    // Find available shared rides near a location
    List<Ride> findShareableRides(Double pickupLat, Double pickupLng, Double radiusKm);
    
    // Request to join a shared ride
    RideShareRequest requestToJoinRide(Long userId, JoinRideRequestDto requestDto);
    
    // Accept a ride share request (by ride owner)
    RideShareRequest acceptRideShareRequest(Long requestId, Long rideOwnerId);
    
    // Reject a ride share request (by ride owner)
    RideShareRequest rejectRideShareRequest(Long requestId, Long rideOwnerId);
    
    // Get all pending requests for a ride
    List<RideShareRequest> getPendingRequestsForRide(Long rideId);
    
    // Get all requests made by a user
    List<RideShareRequest> getUserRideShareRequests(Long userId);
    
    // Calculate individual fare for shared ride
    Double calculateSharedRideFare(Long rideId, Double pickupLat, Double pickupLng, 
                                  Double dropLat, Double dropLng);

}
