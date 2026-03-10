package com.wheelshare.driver.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverEvent {
	private Long driverId;
	private String type;

}
