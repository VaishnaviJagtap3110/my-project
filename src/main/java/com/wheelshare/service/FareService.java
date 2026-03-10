package com.wheelshare.service;

import org.springframework.stereotype.Service;

@Service
public class FareService {
	private static final double BASE_FARE = 30.0;
    private static final double PER_KM_RATE = 10.0;

    public double calculateFare(double distanceKm) {
        return BASE_FARE + (distanceKm * PER_KM_RATE);
    }

}
