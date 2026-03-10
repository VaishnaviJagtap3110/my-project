package com.wheelshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
