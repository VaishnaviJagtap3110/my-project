package com.wheelshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long>{

}
