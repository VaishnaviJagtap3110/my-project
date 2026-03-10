
package com.wheelshare.entity;

import com.wheelshare.util.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, length = 100)
	    private String name;

	    @Column(nullable = false, length = 150)
	    private String email;

	    @Column(nullable = false, length = 15)
	    private String phone;

	    @Column(nullable = false)
	    private String passwordHash;

	    @Column(nullable = false, length = 20)
	    private String role;

	    private Boolean isActive = true;

	    private LocalDateTime createdAt = LocalDateTime.now();

	    private Boolean isEmailVerified = false;

}
