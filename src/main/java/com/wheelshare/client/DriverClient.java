package com.wheelshare.client;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.wheelshare.client.dto.DriverResponseDto;


@FeignClient(
		name = "driver-service",
		url = "http://localhost:8081"
		)
public interface DriverClient{
	
	@GetMapping("/drivers")
	List<DriverResponseDto> getAllDrivers();
	
	
	  @PutMapping("/drivers/{driverId}/ban") 
	  void banDriver(@PathVariable Long
	  driverId);
	 
	  @PutMapping("/drivers/{driverId}/verify")
		void verifyDriver(@PathVariable Long driverId);
	  
	  @PutMapping("/drivers/{driverId}/unban")
	  void unbanDriver(@PathVariable Long driverId);
	
		/*
		 * @PutMapping("/drivers/{driverId}/status") void updateStatus(@PathVariable
		 * Long driverId, @RequestBody DriverStatus status);
		 * 
		 */

}
