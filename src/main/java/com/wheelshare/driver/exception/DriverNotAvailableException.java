package com.wheelshare.driver.exception;

public class DriverNotAvailableException extends RuntimeException {
	public DriverNotAvailableException(Long id) {
		super("Driver not available with id :" + id);
	}
	public DriverNotAvailableException(String reason) {
		super(reason);
	}

}
