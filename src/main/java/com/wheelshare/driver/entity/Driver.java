package com.wheelshare.driver.entity;

import com.wheelshare.driver.util.DriverStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private Long userId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String phone;
	
	/*
	 * @Column(nullable = false) private String phone;
	 */
	@Column(nullable = false)
	private boolean verified = false;
	
	@Column(nullable = false)
	private boolean banned = false;
		
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private DriverStatus status;//MAY CHANGE LATER
	
	public boolean isVerified() {
		return verified;
	}



	public void setVerified(boolean verified) {
		this.verified = verified;
	}



	public boolean isBanned() {
		return banned;
	}

	public void setId(Long id) {
        this.id = id;
    }

	public void setBanned(boolean banned) {
		this.banned = banned;
	}



	public Driver() {}


	
	public Long getId() {
		return id;
	}


		public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

		public DriverStatus getStatus() {
		return status;
	}



	public void setStatus(DriverStatus status) {
		this.status = status;
	}



		public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}

}

	