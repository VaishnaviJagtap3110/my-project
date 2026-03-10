package com.wheelshare.driver.exception;

public class DriverAlreadyExistsException extends RuntimeException {
	public DriverAlreadyExistsException(String email) {
		super("Driver already exists with id :"+ email);
	}

}
