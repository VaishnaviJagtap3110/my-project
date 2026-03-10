package com.wheelshare.driver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheelshare.driver.client.DriverFeignClient;
import com.wheelshare.driver.util.DriverStatus;

@RestController
@RequestMapping("/drivers")
public class FeignTestController {
	private final DriverFeignClient driverFeignClient;
	
	public FeignTestController(DriverFeignClient driverFeignClient) {
		this.driverFeignClient = driverFeignClient;
	}
	
	@GetMapping("/test/assign/{id}")
	public String testAssign(@PathVariable Long id) {
		driverFeignClient.assignDriver(id);
		return "Assign via feign";
	}
	
	@GetMapping("/test/status/{id}")
	public DriverStatus testStatus(@PathVariable Long id) {
		return driverFeignClient.getDriverStatus(id);
	}
	
	@GetMapping("/test/release/{id}")
	public String testRelease(@PathVariable Long id) {
		driverFeignClient.releaseDriver(id);
		return "Released via feign";
	}

}
