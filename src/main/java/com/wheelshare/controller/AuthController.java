package com.wheelshare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wheelshare.client.dto.AuthResponseDto;
import com.wheelshare.client.dto.LoginRequestDto;
import com.wheelshare.client.dto.RegisterRequestDto;
import com.wheelshare.client.dto.VerifyOtpRequestDto;

import com.wheelshare.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
	 private final AuthService authService;

	    //  REGISTER API
	    @PostMapping("/register")
	    public ResponseEntity<AuthResponseDto> register(
	            @RequestBody RegisterRequestDto dto) {

	        return ResponseEntity.ok(
	                authService.registerUser(dto)
	        );
	    }

	    // VERIFY OTP API
	    @PostMapping("/verify-otp")
	    public ResponseEntity<String> verifyOtp(
	            @RequestBody VerifyOtpRequestDto dto) {

	        return ResponseEntity.ok(
	                authService.verifyEmailOtp(dto)
	        );
	    }
	    
	    
	    @PostMapping("/login")
	    public ResponseEntity<AuthResponseDto> login(
	            @RequestBody LoginRequestDto dto) {

	        return ResponseEntity.ok(
	                authService.login(dto)
	        );
	    }

	}


