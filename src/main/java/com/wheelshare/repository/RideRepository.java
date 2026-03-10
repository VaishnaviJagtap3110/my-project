package com.wheelshare.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Long>{
=======
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wheelshare.client.enums.RideStatus;
import com.wheelshare.client.enums.RideType;
import com.wheelshare.entity.Ride;
import org.springframework.data.repository.query.Param;


public interface RideRepository extends JpaRepository<Ride, Long>{
	List<Ride> findByStatus(RideStatus status);

    List<Ride> findByRideOwner_Id(Long rideOwnerId);

    List<Ride> findByDriver_Id(Long driverId);
    
    // Find shareable rides within a certain distance from pickup/drop points
    @Query("SELECT r FROM Ride r WHERE r.isShareable = true " +
           "AND r.status IN ('REQUESTED', 'ACCEPTED') " +
           "AND r.currentPassengers < r.maxPassengers " +
           "AND r.rideType = 'SHARED' " +
           "AND (6371 * acos(cos(radians(:pickupLat)) * cos(radians(r.sourceLat)) * " +
           "cos(radians(r.sourceLng) - radians(:pickupLng)) + " +
           "sin(radians(:pickupLat)) * sin(radians(r.sourceLat)))) <= :radiusKm")
    List<Ride> findShareableRidesNearby(@Param("pickupLat") Double pickupLat,
                                       @Param("pickupLng") Double pickupLng,
                                       @Param("radiusKm") Double radiusKm);
    
    // Find rides by type
    List<Ride> findByRideType(RideType rideType);
    
    // Find available shared rides
    @Query("SELECT r FROM Ride r WHERE r.isShareable = true " +
           "AND r.status IN ('REQUESTED', 'ACCEPTED') " +
           "AND r.currentPassengers < r.maxPassengers")
    List<Ride> findAvailableSharedRides();
>>>>>>> 5c855c18d81fa1f7b7ca2016c8964dc5e1318727

}
