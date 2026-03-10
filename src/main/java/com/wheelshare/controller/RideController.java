package com.wheelshare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheelshare.client.dto.RideRequestDto;
import com.wheelshare.entity.Ride;
import com.wheelshare.service.RideService;

@RestController
@RequestMapping("/api/ride")
public class RideController {
	private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/request")
    public ResponseEntity<Ride> requestRide(
            @RequestBody RideRequestDto request) {

        Ride ride = rideService.requestRide(request);
        return ResponseEntity.ok(ride);
    }

}
