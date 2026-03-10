package com.wheelshare.driver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.driver.entity.Driver;
import com.wheelshare.driver.util.DriverStatus;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	Optional<Driver> findByUserId(Long userId);
	
	List<Driver> findByStatus(DriverStatus status);
	boolean existsByEmail(String email);


}
