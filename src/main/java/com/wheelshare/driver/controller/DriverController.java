package com.wheelshare.driver.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wheelshare.driver.dto.DriverAvailability;
import com.wheelshare.driver.dto.DriverCreateRequestDto;
import com.wheelshare.driver.dto.DriverResponseDto;
import com.wheelshare.driver.service.DriverService;
import com.wheelshare.driver.util.DriverStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@Tag(name = "Driver APIs", description = "Driver management endpoints")
@RestController
@RequestMapping("/drivers")
public class DriverController {
	
	private final DriverService driverService;
	
	public DriverController(DriverService driverService) {
		this.driverService = driverService;
	}
	
	@PostMapping
	public ResponseEntity<DriverResponseDto> createDriver(
	        @RequestBody DriverCreateRequestDto dto) {

	    DriverResponseDto created = driverService.createDriver(dto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}


	@Operation(summary = "Update driver status")
	@PutMapping("/{driverId}/status")
	public String updateStatus(
			@PathVariable Long driverId,
			@RequestBody DriverStatus status) {
		
		driverService.updateAvailability(driverId, status);
		return "Driver status Updated";
	}
	
	@GetMapping("/{id}")
	public DriverResponseDto getDriver(@PathVariable Long id) {
		return driverService.getDriverById(id);
	}
	
	@GetMapping("/available")
	public List<DriverResponseDto> getAvailableDrivers(){
		return driverService.getAvailableDrivers();
	}
	
	@PutMapping("/{driverId}/assign")
	public ResponseEntity<String> assignDriver(@PathVariable Long driverId) {
		driverService.assignDriver(driverId);
		return ResponseEntity.ok("Driver assigned to ride");
	}
	
	@PutMapping("/{driverId}/release")
	@Operation(summary = "Release driver from ride")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Driver released successfully"),
	    @ApiResponse(responseCode = "404", description = "Driver not found"),
	    @ApiResponse(responseCode = "409", description = "Driver not on ride")
	})
	public ResponseEntity<String> releaseDriver(@PathVariable Long driverId) {
		driverService.releaseDriver(driverId);
		
		return ResponseEntity.ok("Driver released");
	}
	
	@GetMapping("/{driverId}/availability")
	public DriverStatus getAvailability(@PathVariable Long driverId) {
		return driverService.getDriverStatus(driverId);
	}
	
	
	@PutMapping("/{driverId}/verify")
	@Operation(summary = "Verify driver documents")
	public ResponseEntity<String> verifyDriver(@PathVariable Long driverId){
		driverService.verifyDriver(driverId);
		return ResponseEntity.ok("Driver verified");
	}
	@PutMapping("/{driverId}/ban")
	@Operation(summary = "Ban driver")
	public ResponseEntity<String> banDriver(@PathVariable Long driverId){
		driverService.banDriver(driverId);
		return ResponseEntity.ok("Driver banned");
	}
	@PutMapping("/{driverId}/unban")
	@Operation(summary = "Unban driver")
	public ResponseEntity<String> unbanDriver(@PathVariable Long driverId){
		driverService.unbanDriver(driverId);
		return ResponseEntity.ok("Driver unbanned");
	}
	@GetMapping
	public List<DriverResponseDto> getAllDrivers(){
		return driverService.getAllDrivers();
	}
	
	
	
	
	

}
