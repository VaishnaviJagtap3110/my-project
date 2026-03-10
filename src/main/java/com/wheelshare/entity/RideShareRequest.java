package com.wheelshare.entity;

import java.time.LocalDateTime;

import com.wheelshare.client.enums.RideShareRequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ride_share_requests")
public class RideShareRequest {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    // Requester's pickup location
    private Double pickupLat;
    private Double pickupLng;
    private String pickupAddress;

    // Requester's drop location
    private Double dropLat;
    private Double dropLng;
    private String dropAddress;

    // Estimated fare for this passenger
    private Double estimatedFare;

    @Enumerated(EnumType.STRING)
    private RideShareRequestStatus status;

    private LocalDateTime requestedAt;
    private LocalDateTime respondedAt;

    // Message from requester
    private String message;

    public RideShareRequest() {
    }

    public RideShareRequest(Long id, Ride ride, User requester, Double pickupLat, Double pickupLng,
            String pickupAddress, Double dropLat, Double dropLng, String dropAddress, Double estimatedFare,
            RideShareRequestStatus status, LocalDateTime requestedAt, LocalDateTime respondedAt, String message) {
        this.id = id;
        this.ride = ride;
        this.requester = requester;
        this.pickupLat = pickupLat;
        this.pickupLng = pickupLng;
        this.pickupAddress = pickupAddress;
        this.dropLat = dropLat;
        this.dropLng = dropLng;
        this.dropAddress = dropAddress;
        this.estimatedFare = estimatedFare;
        this.status = status;
        this.requestedAt = requestedAt;
        this.respondedAt = respondedAt;
        this.message = message;
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

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public Double getPickupLat() {
        return pickupLat;
    }

    public void setPickupLat(Double pickupLat) {
        this.pickupLat = pickupLat;
    }

    public Double getPickupLng() {
        return pickupLng;
    }

    public void setPickupLng(Double pickupLng) {
        this.pickupLng = pickupLng;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public Double getDropLat() {
        return dropLat;
    }

    public void setDropLat(Double dropLat) {
        this.dropLat = dropLat;
    }

    public Double getDropLng() {
        return dropLng;
    }

    public void setDropLng(Double dropLng) {
        this.dropLng = dropLng;
    }

    public String getDropAddress() {
        return dropAddress;
    }

    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }

    public Double getEstimatedFare() {
        return estimatedFare;
    }

    public void setEstimatedFare(Double estimatedFare) {
        this.estimatedFare = estimatedFare;
    }

    public RideShareRequestStatus getStatus() {
        return status;
    }

    public void setStatus(RideShareRequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public LocalDateTime getRespondedAt() {
        return respondedAt;
    }

    public void setRespondedAt(LocalDateTime respondedAt) {
        this.respondedAt = respondedAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Builder {
        private Long id;
        private Ride ride;
        private User requester;
        private Double pickupLat;
        private Double pickupLng;
        private String pickupAddress;
        private Double dropLat;
        private Double dropLng;
        private String dropAddress;
        private Double estimatedFare;
        private RideShareRequestStatus status;
        private LocalDateTime requestedAt;
        private LocalDateTime respondedAt;
        private String message;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder ride(Ride ride) {
            this.ride = ride;
            return this;
        }

        public Builder requester(User requester) {
            this.requester = requester;
            return this;
        }

        public Builder pickupLat(Double pickupLat) {
            this.pickupLat = pickupLat;
            return this;
        }

        public Builder pickupLng(Double pickupLng) {
            this.pickupLng = pickupLng;
            return this;
        }

        public Builder pickupAddress(String pickupAddress) {
            this.pickupAddress = pickupAddress;
            return this;
        }

        public Builder dropLat(Double dropLat) {
            this.dropLat = dropLat;
            return this;
        }

        public Builder dropLng(Double dropLng) {
            this.dropLng = dropLng;
            return this;
        }

        public Builder dropAddress(String dropAddress) {
            this.dropAddress = dropAddress;
            return this;
        }

        public Builder estimatedFare(Double estimatedFare) {
            this.estimatedFare = estimatedFare;
            return this;
        }

        public Builder status(RideShareRequestStatus status) {
            this.status = status;
            return this;
        }

        public Builder requestedAt(LocalDateTime requestedAt) {
            this.requestedAt = requestedAt;
            return this;
        }

        public Builder respondedAt(LocalDateTime respondedAt) {
            this.respondedAt = respondedAt;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public RideShareRequest build() {
            return new RideShareRequest(id, ride, requester, pickupLat, pickupLng, pickupAddress, dropLat, dropLng,
                    dropAddress, estimatedFare, status, requestedAt, respondedAt, message);
        }
    }

}
