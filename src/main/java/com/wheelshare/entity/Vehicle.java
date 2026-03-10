package com.wheelshare.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicleId;

    @Column(nullable = false)
    private Integer driverId;

    @Column(nullable = false, length = 20)
    private String vehicleType;

    @Column(nullable = false, length = 20)
    private String vehicleNumber;

    private Integer seats;

    private Boolean isActive = true;

    

}
