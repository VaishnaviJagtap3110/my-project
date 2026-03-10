package com.wheelshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.wheelshare.client")
public class WheelshareApplication {

	public static void main(String[] args) {
		SpringApplication.run(WheelshareApplication.class, args);
	}

}
