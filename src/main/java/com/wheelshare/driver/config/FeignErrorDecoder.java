package com.wheelshare.driver.config;



import com.wheelshare.driver.exception.DriverNotAvailableException;
import com.wheelshare.driver.exception.DriverNotFoundException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		
		switch (response.status()) {
        case 404:
        	throw new RuntimeException("Ride service error: " + response.status());


        case 409:
            return new DriverNotAvailableException("Driver not available");

        case 400:
            return new RuntimeException("Bad request from downstream service");

        default:
            return new RuntimeException(
                    "Feign error: HTTP " + response.status()
            );
    }
		
		//return null;
	}

}
