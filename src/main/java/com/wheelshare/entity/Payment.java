package com.wheelshare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

	@Column(name = "RideId", nullable = false)
	private Long rideId;
	

    @OneToOne
    @JoinColumn(name = "RideId", insertable = false, updatable = false)
    private Ride ride;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private String paymentStatus;

    private String transactionRef;

    private LocalDateTime createdAt = LocalDateTime.now();
    
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

}
