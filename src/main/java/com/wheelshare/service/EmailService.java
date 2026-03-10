package com.wheelshare.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {
	public void sendOtpEmail(String email, String otp) {

        // Later replace with real email service
        log.info("OTP for {} is {}", email, otp);

    }

}
