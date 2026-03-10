package com.wheelshare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wheelshare.entity.RideShareRequest;
import org.springframework.data.repository.query.Param;


public interface RideShareRequestRepository extends JpaRepository<RideShareRequest, Long> {
	List<RideShareRequest> findByRideId(Long rideId);
    List<RideShareRequest> findByRequesterId(Long requesterId);
    
    // Find pending requests for a specific ride
    @Query("SELECT r FROM RideShareRequest r WHERE r.ride.id = :rideId AND r.status = 'PENDING'")
    List<RideShareRequest> findPendingRequestsByRideId(@Param("rideId") Long rideId);
    
    // Find all requests made by a user
    @Query("SELECT r FROM RideShareRequest r WHERE r.requester.id = :userId")
    List<RideShareRequest> findRequestsByUserId(@Param("userId") Long userId);
	

}
