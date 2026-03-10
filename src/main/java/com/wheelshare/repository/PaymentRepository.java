package com.wheelshare.repository;

<<<<<<< HEAD
=======
import java.util.Optional;

>>>>>>> 5c855c18d81fa1f7b7ca2016c8964dc5e1318727
import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.Payment;

<<<<<<< HEAD
public interface PaymentRepository extends JpaRepository<Payment, Long>{
=======
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);
>>>>>>> 5c855c18d81fa1f7b7ca2016c8964dc5e1318727

}
