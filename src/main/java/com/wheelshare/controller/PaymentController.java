package com.wheelshare.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.razorpay.Order;
import com.wheelshare.entity.*;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.wheelshare.entity.Ride;
import com.wheelshare.repository.PaymentRepository;
import com.wheelshare.repository.RideRepository;

public class PaymentController {
	private final RideRepository rideRepository;
	private final PaymentRepository paymentRepository;
	private String keySecret;
	
	
	public PaymentController(RideRepository rideRepository,PaymentRepository paymentRepository) {
		super();
		this.rideRepository = rideRepository;
		this.paymentRepository = paymentRepository;
	}



	@PostMapping("/create")
	public ResponseEntity<?> createOrder(
	        @RequestParam Integer rideId) 
	        throws RazorpayException {

	    Ride ride = rideRepository.findById(rideId)
	            .orElseThrow();

	    Double amount = ride.getTotalFare();

	    RazorpayClient client =
	            new RazorpayClient(keyId, keySecret);

	    JSONObject options = new JSONObject();
	    options.put("amount", amount.multiply(BigDecimal.valueOf(100)));
	    options.put("currency", "INR");
	    options.put("receipt", "ride_" + rideId);

	    Order order = client.orders.create(options);

	    Payment payment = Payment.builder()
	            .rideId(rideId)
	            .amount(amount)
	            .paymentMethod("RAZORPAY")
	            .paymentStatus("CREATED")
	            .razorpayOrderId(order.get("id"))
	            .createdAt(LocalDateTime.now())
	            .build();

	    paymentRepository.save(payment);

	    return ResponseEntity.ok(order.toString());
	}
	
	@PostMapping("/verify")
	public ResponseEntity<?> verifyPayment(
	        @RequestBody Map<String, String> data)
	        throws RazorpayException {

	    String orderId = data.get("razorpay_order_id");
	    String paymentId = data.get("razorpay_payment_id");
	    String signature = data.get("razorpay_signature");

	    JSONObject options = new JSONObject();
	    options.put("razorpay_order_id", orderId);
	    options.put("razorpay_payment_id", paymentId);
	    options.put("razorpay_signature", signature);

	    boolean isValid =
	            Utils.verifyPaymentSignature(options, keySecret);

	    com.wheelshare.entity.Payment payment = paymentRepository
	            .findByRazorpayOrderId(orderId)
	            .orElseThrow();

	    if (isValid) {
	        payment.setPaymentStatus("SUCCESS");
	        payment.setRazorpayPaymentId(paymentId);
	        payment.setRazorpaySignature(signature);
	        payment.setTransactionRef(paymentId);

	        // Update ride status
	        Ride ride = payment.getRide();
	        ride.setStatus("CONFIRMED");

	        rideRepository.save(ride);

	    } else {
	        payment.setPaymentStatus("FAILED");
	    }

	    paymentRepository.save(payment);

	    return ResponseEntity.ok("Verified");
	}

}
