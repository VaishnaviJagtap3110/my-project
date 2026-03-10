package com.wheelshare.client.dto;

import com.wheelshare.client.enums.DriverStatus;

public class DriverResponseDto {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private DriverStatus status;
	
	public DriverResponseDto(Long id, String name,String email, String phone, DriverStatus status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public DriverStatus getStatus() {
		return status;
	}
	


}
