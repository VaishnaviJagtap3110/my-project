package com.wheelshare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheelshare.client.DriverClient;
import com.wheelshare.client.dto.DriverResponseDto;
import com.wheelshare.client.enums.DriverStatus;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private final DriverClient driverClient;

	public AdminController(DriverClient driverClient) {
		this.driverClient = driverClient;
	}
	
	@GetMapping("/drivers")
	public List<DriverResponseDto> getAllDrivers(){
		return driverClient.getAllDrivers();
	}
	
	@PutMapping("/drivers/{driverId}/ban")
	public ResponseEntity<String> banDriver(@PathVariable Long driverId){
		driverClient.banDriver(driverId);
		return ResponseEntity.ok("Driver is banned");
	}
	
	@PutMapping("/drivers/{driverId}/verify")
	public ResponseEntity<String> verifyDriver(@PathVariable Long driverId){
		driverClient.verifyDriver(driverId);
		return ResponseEntity.ok("Driver verified");
	}
	
	@PutMapping("/drivers/{driverId}/unban")
	public ResponseEntity<String> unbanDriver(@PathVariable Long driverId){
	    driverClient.unbanDriver(driverId);
	    return ResponseEntity.ok("Driver unbanned");
	}

	
	//unban remain
	
	
	

}
