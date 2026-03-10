package com.wheelshare.service;

import com.wheelshare.client.dto.RideRequestDto;
import com.wheelshare.entity.Ride;

public interface RideService {
	Ride requestRide(RideRequestDto request);

}
