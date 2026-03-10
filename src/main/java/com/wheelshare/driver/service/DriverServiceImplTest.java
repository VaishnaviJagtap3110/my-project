package com.wheelshare.driver.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.wheelshare.driver.entity.Driver;
import com.wheelshare.driver.exception.DriverNotAvailableException;
import com.wheelshare.driver.exception.DriverNotFoundException;
import com.wheelshare.driver.repository.DriverRepository;
import com.wheelshare.driver.util.DriverStatus;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {

	/*
	 * @Mock
	 * 
	 * private DriverEventPublisher eventPublisher;
	 */
    @Mock
    private DriverRepository driverRepository;


    @InjectMocks
    private DriverServiceImpl driverService;

    @Test
    void assignDriver_success_whenDriverOnline() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.ONLINE);
        driver.setVerified(true);
        driver.setBanned(false);

        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        driverService.assignDriver(1L);

        assertEquals(DriverStatus.ON_RIDE, driver.getStatus());
        verify(driverRepository).save(driver);
    }
    
    @Test
    void releaseDriver_success_whenOnRide() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.ON_RIDE);
        driver.setVerified(true);
        driver.setBanned(false);

        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        driverService.releaseDriver(1L);

        assertEquals(DriverStatus.ONLINE, driver.getStatus());
        verify(driverRepository).save(driver);
    }


    @Test
    void assignDriver_fail_whenDriverOffline() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.OFFLINE);
        driver.setVerified(true);
        driver.setBanned(false);

        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        assertThrows(
                DriverNotAvailableException.class,
                () -> driverService.assignDriver(1L)
        );
    }

    @Test
    void getDriverStatus_fail_whenDriverNotFound() {
        when(driverRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(
                DriverNotFoundException.class,
                () -> driverService.getDriverStatus(99L)
        );
    }
}
