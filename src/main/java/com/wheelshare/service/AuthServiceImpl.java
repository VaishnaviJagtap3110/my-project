package com.wheelshare.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wheelshare.client.dto.AuthResponseDto;
import com.wheelshare.client.dto.LoginRequestDto;
import com.wheelshare.client.dto.RegisterRequestDto;
import com.wheelshare.client.dto.VerifyOtpRequestDto;
import com.wheelshare.entity.EmailOtp;
import com.wheelshare.entity.User;
import com.wheelshare.repository.UserRepository;
import com.wheelshare.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final UserRepository userRepository;
    private final EmailOtpService emailOtpService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    // REGISTER USER
    public AuthResponseDto registerUser(RegisterRequestDto dto) {

        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("Email already registered");
                });

        User user = User.builder()
                .name(dto.getName()) //MAPPING REQ. DATA TO ENTITY-. meaning whatever data came from request- map to entity
                .email(dto.getEmail())
                .phone(dto.getPhone())
                /*
                 * Takes the plain password entered by user

Converts it into a hashed (encrypted) form

Stores the hashed version in the database
                 */
                .passwordHash(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .isActive(true)
                .isEmailVerified(false)        
                .build();       // Creates the User object in memory

        User savedUser = userRepository.save(user);  // User is stored in DB

        EmailOtp otp = emailOtpService.createOtp(savedUser.getId());  // Generates a random OTP

        emailService.sendOtpEmail(savedUser.getEmail(), otp.getOtpCode());  // Sends email to user which -. during register

        return AuthResponseDto.builder()
                .message("User registered. OTP sent.")
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .build();
    }

    
    
    public String verifyEmailOtp(VerifyOtpRequestDto dto) {

        boolean valid = emailOtpService
                .validateOtp(dto.getUserId(), dto.getOtpCode());

        if(valid) {

            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setIsEmailVerified(true);
            userRepository.save(user);

            return "Email verified successfully";
        }

        return "OTP verification failed";
    }
    
    
    public AuthResponseDto login(LoginRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!user.getIsEmailVerified()) {
            throw new RuntimeException("Email not verified");
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole()
        );


        return AuthResponseDto.builder()
                .message("Login successful")
                .userId(user.getId())
                .email(user.getEmail())
                .token(token)
                .build();
    }



}
