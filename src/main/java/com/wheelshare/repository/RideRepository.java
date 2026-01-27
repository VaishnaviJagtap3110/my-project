package com.wheelshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Long>{

}
