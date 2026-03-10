package com.wheelshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
