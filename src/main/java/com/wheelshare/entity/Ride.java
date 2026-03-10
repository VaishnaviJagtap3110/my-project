package com.wheelshare.entity;
import java.util.List;
import com.wheelshare.client.enums.RideStatus;
import com.wheelshare.client.enums.RideType;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Rides")
public class Ride {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// Who created/booked the ride (ride owner)
	@ManyToOne
	@JoinColumn(name = "ride_owner_id", nullable = false)
	private User rideOwner;

	// Who accepted the ride (nullable until accepted)
	@ManyToOne
	@JoinColumn(name = "driver_id")
	private User driver;

	// Main route coordinates
	private Double sourceLat;
	private Double sourceLng;
	private String sourceAddress;

	private Double destinationLat;
	private Double destinationLng;
	private String destinationAddress;

	private Double distanceKm;
	private Double totalFare;

	@Enumerated(EnumType.STRING)
	private RideStatus status;

	@Enumerated(EnumType.STRING)
	private RideType rideType;

	// Maximum passengers allowed for shared rides
	private Integer maxPassengers = 4;

	// Current passenger count
	private Integer currentPassengers = 1;

	// Is this ride available for sharing?
	private Boolean isShareable = false;

	private Boolean isPaid = false;

	private LocalDateTime requestedAt;
	private LocalDateTime acceptedAt;
	private LocalDateTime startedAt;
	private LocalDateTime completedAt;

	// All passengers in this ride (including owner)
	@OneToMany(mappedBy = "ride", cascade = CascadeType.ALL)
	private List<RidePassenger> passengers;

	// Ride share requests for this ride
	@OneToMany(mappedBy = "ride", cascade = CascadeType.ALL)
	private List<RideShareRequest> shareRequests;

	public Ride() {
	}

	public Ride(Long id, User rideOwner, User driver, Double sourceLat, Double sourceLng, String sourceAddress,
			Double destinationLat, Double destinationLng, String destinationAddress, Double distanceKm,
			Double totalFare, RideStatus status, RideType rideType, Integer maxPassengers, Integer currentPassengers,
			Boolean isShareable, Boolean isPaid, LocalDateTime requestedAt, LocalDateTime acceptedAt,
			LocalDateTime startedAt, LocalDateTime completedAt, List<RidePassenger> passengers,
			List<RideShareRequest> shareRequests) {
		this.id = id;
		this.rideOwner = rideOwner;
		this.driver = driver;
		this.sourceLat = sourceLat;
		this.sourceLng = sourceLng;
		this.sourceAddress = sourceAddress;
		this.destinationLat = destinationLat;
		this.destinationLng = destinationLng;
		this.destinationAddress = destinationAddress;
		this.distanceKm = distanceKm;
		this.totalFare = totalFare;
		this.status = status;
		this.rideType = rideType;
		this.maxPassengers = maxPassengers;
		this.currentPassengers = currentPassengers;
		this.isShareable = isShareable;
		this.isPaid = isPaid;
		this.requestedAt = requestedAt;
		this.acceptedAt = acceptedAt;
		this.startedAt = startedAt;
		this.completedAt = completedAt;
		this.passengers = passengers;
		this.shareRequests = shareRequests;
	}

	public static Builder builder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getRideOwner() {
		return rideOwner;
	}

	public void setRideOwner(User rideOwner) {
		this.rideOwner = rideOwner;
	}

	public User getDriver() {
		return driver;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}

	public Double getSourceLat() {
		return sourceLat;
	}

	public void setSourceLat(Double sourceLat) {
		this.sourceLat = sourceLat;
	}

	public Double getSourceLng() {
		return sourceLng;
	}

	public void setSourceLng(Double sourceLng) {
		this.sourceLng = sourceLng;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public Double getDestinationLat() {
		return destinationLat;
	}

	public void setDestinationLat(Double destinationLat) {
		this.destinationLat = destinationLat;
	}

	public Double getDestinationLng() {
		return destinationLng;
	}

	public void setDestinationLng(Double destinationLng) {
		this.destinationLng = destinationLng;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public Double getDistanceKm() {
		return distanceKm;
	}

	public void setDistanceKm(Double distanceKm) {
		this.distanceKm = distanceKm;
	}

	public Double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}

	public RideStatus getStatus() {
		return status;
	}

	public void setStatus(RideStatus status) {
		this.status = status;
	}

	public RideType getRideType() {
		return rideType;
	}

	public void setRideType(RideType rideType) {
		this.rideType = rideType;
	}

	public Integer getMaxPassengers() {
		return maxPassengers;
	}

	public void setMaxPassengers(Integer maxPassengers) {
		this.maxPassengers = maxPassengers;
	}

	public Integer getCurrentPassengers() {
		return currentPassengers;
	}

	public void setCurrentPassengers(Integer currentPassengers) {
		this.currentPassengers = currentPassengers;
	}

	public Boolean getIsShareable() {
		return isShareable;
	}

	public void setIsShareable(Boolean isShareable) {
		this.isShareable = isShareable;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public void setPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public LocalDateTime getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(LocalDateTime requestedAt) {
		this.requestedAt = requestedAt;
	}

	public LocalDateTime getAcceptedAt() {
		return acceptedAt;
	}

	public void setAcceptedAt(LocalDateTime acceptedAt) {
		this.acceptedAt = acceptedAt;
	}

	public LocalDateTime getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}

	public List<RidePassenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<RidePassenger> passengers) {
		this.passengers = passengers;
	}

	public List<RideShareRequest> getShareRequests() {
		return shareRequests;
	}

	public void setShareRequests(List<RideShareRequest> shareRequests) {
		this.shareRequests = shareRequests;
	}

	public static class Builder {
		private Long id;
		private User rideOwner;
		private User driver;
		private Double sourceLat;
		private Double sourceLng;
		private String sourceAddress;
		private Double destinationLat;
		private Double destinationLng;
		private String destinationAddress;
		private Double distanceKm;
		private Double totalFare;
		private RideStatus status;
		private RideType rideType;
		private Integer maxPassengers = 4;
		private Integer currentPassengers = 1;
		private Boolean isShareable = false;
		private Boolean isPaid = false;
		private LocalDateTime requestedAt;
		private LocalDateTime acceptedAt;
		private LocalDateTime startedAt;
		private LocalDateTime completedAt;
		private List<RidePassenger> passengers;
		private List<RideShareRequest> shareRequests;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder rideOwner(User rideOwner) {
			this.rideOwner = rideOwner;
			return this;
		}

		public Builder driver(User driver) {
			this.driver = driver;
			return this;
		}

		public Builder sourceLat(Double sourceLat) {
			this.sourceLat = sourceLat;
			return this;
		}

		public Builder sourceLng(Double sourceLng) {
			this.sourceLng = sourceLng;
			return this;
		}

		public Builder sourceAddress(String sourceAddress) {
			this.sourceAddress = sourceAddress;
			return this;
		}

		public Builder destinationLat(Double destinationLat) {
			this.destinationLat = destinationLat;
			return this;
		}

		public Builder destinationLng(Double destinationLng) {
			this.destinationLng = destinationLng;
			return this;
		}

		public Builder destinationAddress(String destinationAddress) {
			this.destinationAddress = destinationAddress;
			return this;
		}

		public Builder distanceKm(Double distanceKm) {
			this.distanceKm = distanceKm;
			return this;
		}

		public Builder totalFare(Double totalFare) {
			this.totalFare = totalFare;
			return this;
		}

		public Builder status(RideStatus status) {
			this.status = status;
			return this;
		}

		public Builder rideType(RideType rideType) {
			this.rideType = rideType;
			return this;
		}

		public Builder maxPassengers(Integer maxPassengers) {
			this.maxPassengers = maxPassengers;
			return this;
		}

		public Builder currentPassengers(Integer currentPassengers) {
			this.currentPassengers = currentPassengers;
			return this;
		}

		public Builder isShareable(Boolean isShareable) {
			this.isShareable = isShareable;
			return this;
		}

		public Builder isPaid(Boolean isPaid) {
			this.isPaid = isPaid;
			return this;
		}

		public Builder requestedAt(LocalDateTime requestedAt) {
			this.requestedAt = requestedAt;
			return this;
		}

		public Builder acceptedAt(LocalDateTime acceptedAt) {
			this.acceptedAt = acceptedAt;
			return this;
		}

		public Builder startedAt(LocalDateTime startedAt) {
			this.startedAt = startedAt;
			return this;
		}

		public Builder completedAt(LocalDateTime completedAt) {
			this.completedAt = completedAt;
			return this;
		}

		public Builder passengers(List<RidePassenger> passengers) {
			this.passengers = passengers;
			return this;
		}

		public Builder shareRequests(List<RideShareRequest> shareRequests) {
			this.shareRequests = shareRequests;
			return this;
		}

		public Ride build() {
			return new Ride(id, rideOwner, driver, sourceLat, sourceLng, sourceAddress, destinationLat, destinationLng,
					destinationAddress, distanceKm, totalFare, status, rideType, maxPassengers, currentPassengers,
					isShareable, isPaid, requestedAt, acceptedAt, startedAt, completedAt, passengers, shareRequests);
		}
	}

}
