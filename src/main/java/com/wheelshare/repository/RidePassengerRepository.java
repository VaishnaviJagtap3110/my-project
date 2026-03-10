package com.wheelshare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.RidePassenger;

public interface RidePassengerRepository extends JpaRepository<RidePassenger, Long> {
	List<RidePassenger> findByRideId(Long rideId);
    List<RidePassenger> findByPassengerId(Long passengerId);
    
    // Find passengers who haven't been picked up yet
    List<RidePassenger> findByRideIdAndIsPickedUpFalse(Long rideId);
    
    // Find passengers who haven't been dropped yet
    List<RidePassenger> findByRideIdAndIsDroppedFalse(Long rideId);

}
