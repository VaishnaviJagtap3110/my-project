package com.wheelshare.driver.service;

import java.util.List;

//import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.wheelshare.driver.dto.DriverCreateRequestDto;
import com.wheelshare.driver.dto.DriverRequestDto;
import com.wheelshare.driver.dto.DriverResponseDto;
import com.wheelshare.driver.entity.Driver;
import com.wheelshare.driver.event.DriverEvent;
import com.wheelshare.driver.exception.DriverAlreadyExistsException;
import com.wheelshare.driver.exception.DriverNotAvailableException;
import com.wheelshare.driver.exception.DriverNotFoundException;
import com.wheelshare.driver.messaging.DriverEventPublisher;
import com.wheelshare.driver.repository.DriverRepository;
import com.wheelshare.driver.util.DriverStatus;

@Service
public class DriverServiceImpl implements DriverService {
		
		private final DriverRepository driverRepository;
		private final DriverEventPublisher eventPublisher;
		
		public DriverServiceImpl(DriverRepository driverRepository,DriverEventPublisher eventPublisher) {
			this.driverRepository = driverRepository;
			this.eventPublisher = eventPublisher;
		}
		
		@Override
		public DriverResponseDto createDriver(DriverCreateRequestDto dto) {
			if (driverRepository.existsByEmail(dto.getEmail())) {
		        throw new DriverAlreadyExistsException(dto.getEmail());
		    }
			Driver driver = new Driver();
			driver.setUserId(dto.getUserId());
	        driver.setName(dto.getName());
	        driver.setEmail(dto.getEmail());
	        driver.setPhone(dto.getPhone());
	        driver.setStatus(DriverStatus.OFFLINE);
			
			Driver saved=  driverRepository.save(driver);
			return mapToDto(saved);
		}

	@Override
	public void updateAvailability(Long driverId, DriverStatus status) {
		Driver driver = driverRepository.findById(driverId)
				.orElseThrow(()-> new DriverNotFoundException(driverId));
		driver.setStatus(status);
		driverRepository.save(driver);
	}
	
	@Override
	public DriverResponseDto getDriverById(Long id) {
		Driver driver = driverRepository.findById(id)
				.orElseThrow(()-> new DriverNotFoundException(id));
		
		return new DriverResponseDto(driver.getId(), driver.getName(), driver.getEmail(), driver.getPhone(), driver.getStatus());
	}
	
	@Override
	public List<DriverResponseDto> getAvailableDrivers(){
		return driverRepository.findByStatus(DriverStatus.ONLINE)
				.stream()
				.map(driver -> new DriverResponseDto(driver.getId(), driver.getName(), driver.getEmail(), driver.getPhone(), driver.getStatus()))
						.toList();
	}
	
	@Override
	public void assignDriver(Long driverId) {
		Driver driver = driverRepository.findById(driverId)
				.orElseThrow(()-> new DriverNotFoundException(driverId));
	
	if(driver.isBanned()) {
		throw new DriverNotAvailableException("Driver is banned");
	}
	if(!driver.isVerified()) {
		throw new DriverNotAvailableException("Driver not verified");
	}
		
		if(driver.getStatus() != DriverStatus.ONLINE) {
		throw new DriverNotAvailableException(driverId);
	}
	
	driver.setStatus(DriverStatus.ON_RIDE);
	driverRepository.save(driver);
	}
	
	@Override
	public void releaseDriver(Long driverId) {
		Driver driver = driverRepository.findById(driverId)
				.orElseThrow(()-> new DriverNotFoundException(driverId));
				
				if(driver.getStatus() != DriverStatus.ON_RIDE) {
					throw new DriverNotAvailableException(driverId);
				}
				driver.setStatus(DriverStatus.ONLINE);
				driverRepository.save(driver);
	}
	
	@Override
	public DriverStatus getDriverStatus(Long driverId) {
		Driver driver = driverRepository.findById(driverId)
				.orElseThrow(()-> new DriverNotFoundException(driverId));
				return driver.getStatus();
	}
	
	
	
	@Override
	public void verifyDriver(Long driverId) {
		Driver driver = driverRepository.findById(driverId)
				.orElseThrow(()-> new DriverNotFoundException(driverId));
		driver.setVerified(true);
		driverRepository.save(driver);
		
		eventPublisher.publish(new DriverEvent(driverId, "DRIVER_VERIFIED"));
	}
	
	@Override
	public void banDriver(Long driverId) {
		Driver driver = driverRepository.findById(driverId)
				.orElseThrow(()-> new DriverNotFoundException(driverId));
		driver.setBanned(true);
		driver.setStatus(DriverStatus.OFFLINE);
		driverRepository.save(driver);
	}
	
	@Override
	public void unbanDriver(Long driverId) {
		Driver driver = driverRepository.findById(driverId)
				.orElseThrow(()-> new DriverNotFoundException(driverId));
		driver.setBanned(false);
		driverRepository.save(driver);
		
		eventPublisher.publish(new DriverEvent(driverId, "DRIVER_UNBANNED"));

	}
	
	

}



