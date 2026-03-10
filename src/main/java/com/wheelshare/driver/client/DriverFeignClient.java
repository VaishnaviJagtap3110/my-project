package com.wheelshare.driver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.wheelshare.driver.config.FeignConfig;
import com.wheelshare.driver.util.DriverStatus;

@FeignClient(
		name = "ride-service",
		url = "http://localhost:8082",
		configuration = FeignConfig.class
		)
public interface DriverFeignClient {
	@PutMapping("/drivers/{driverId}/assign")
	void assignDriver(@PathVariable("driverId") Long driverId);
	
	@PutMapping("/drivers/{driverId}/release")
	void releaseDriver(@PathVariable("driverId") Long driverId);
	
	@GetMapping("/drivers/{driverId}/availability")
	DriverStatus getDriverStatus(@PathVariable("driverId") Long driverId);
	//calling itself temp.

}
