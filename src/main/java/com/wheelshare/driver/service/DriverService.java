package com.wheelshare.driver.service;

import java.util.List;

import com.wheelshare.driver.dto.DriverCreateRequestDto;
import com.wheelshare.driver.dto.DriverRequestDto;
import com.wheelshare.driver.dto.DriverResponseDto;
import com.wheelshare.driver.util.DriverStatus;

public interface DriverService {
	DriverResponseDto createDriver(DriverCreateRequestDto dto);
	void updateAvailability(Long driverId, DriverStatus status);
	
	DriverResponseDto getDriverById(Long id);
	List<DriverResponseDto> getAvailableDrivers();
	void assignDriver(Long driverId);
	void releaseDriver(Long driverId);
	DriverStatus getDriverStatus(Long driverId);
	void verifyDriver(Long driverId);
	void banDriver(Long driverId);
	void unbanDriver(Long driverId);
	List<DriverResponseDto> getAllDrivers();


}
