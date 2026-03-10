package com.wheelshare.service;

import com.wheelshare.client.dto.AuthResponseDto;
import com.wheelshare.client.dto.LoginRequestDto;
import com.wheelshare.client.dto.RegisterRequestDto;
import com.wheelshare.client.dto.VerifyOtpRequestDto;

public interface AuthService {
	    AuthResponseDto registerUser(RegisterRequestDto dto);
	    String verifyEmailOtp(VerifyOtpRequestDto dto);
	    AuthResponseDto login(LoginRequestDto dto);
	

}
