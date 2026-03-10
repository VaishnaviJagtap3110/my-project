package com.wheelshare.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.wheelshare.entity.EmailOtp;
import com.wheelshare.repository.EmailOtpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailOtpService {
	private final EmailOtpRepository emailOtpRepository;

    //  Generate 6 digit OTP
    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    //  Create OTP record in DB
    public EmailOtp createOtp(Long userId) {

        String otpCode = generateOtp();

        EmailOtp otp = EmailOtp.builder()
                .userId(userId)
                .otpCode(otpCode)
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .isUsed(false)
                .build();

        return emailOtpRepository.save(otp);
    }

    //  Validate OTP
    public boolean validateOtp(Long userId, String otpCode) {

        EmailOtp latestOtp = emailOtpRepository
                .findTopByUserIdOrderByOtpIdDesc(userId)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if(latestOtp.getIsUsed())
            throw new RuntimeException("OTP already used");

        if(latestOtp.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new RuntimeException("OTP expired");

        if(!latestOtp.getOtpCode().equals(otpCode))
            throw new RuntimeException("Invalid OTP");

        latestOtp.setIsUsed(true);
        emailOtpRepository.save(latestOtp);

        return true;
    }

}
