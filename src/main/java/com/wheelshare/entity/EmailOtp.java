package com.wheelshare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "EmailOtps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailOtp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer otpId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String otpCode;

    private LocalDateTime expiresAt;

    private Boolean isUsed = false;

    @ManyToOne
    @JoinColumn(name = "UserId", insertable = false, updatable = false)
    private User user;

}
