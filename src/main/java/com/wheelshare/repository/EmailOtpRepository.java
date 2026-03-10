package com.wheelshare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.EmailOtp;

public interface EmailOtpRepository extends JpaRepository<EmailOtp, Integer> {
	Optional<EmailOtp> findTopByUserIdOrderByOtpIdDesc(Long userId);

}
