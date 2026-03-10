package com.wheelshare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);

}
