package com.wheelshare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name ="drivers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Driver extends User {
	
	private String vehicleName;
	private String vehicleType;
	
	private boolean isAvailable;
	
	private Double currentLatitude;
	private Double currentLongitude;
	
	private Double ratings;
	private Integer totalRides;

}
