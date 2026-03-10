package com.wheelshare.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ride_passengers")
public class RidePassenger {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    // Individual pickup location for this passenger
    private Double pickupLat;
    private Double pickupLng;
    private String pickupAddress;

    // Individual drop location for this passenger
    private Double dropLat;
    private Double dropLng;
    private String dropAddress;

    // Individual fare for this passenger
    private Double individualFare;

    // Status for this specific passenger
    private boolean isPickedUp = false;
    private boolean isDropped = false;

    private LocalDateTime joinedAt;
    private LocalDateTime pickedUpAt;
    private LocalDateTime droppedAt;

    public RidePassenger() {
    }

    public RidePassenger(Long id, Ride ride, User passenger, Double pickupLat, Double pickupLng, String pickupAddress,
            Double dropLat, Double dropLng, String dropAddress, Double individualFare, boolean isPickedUp,
            boolean isDropped, LocalDateTime joinedAt, LocalDateTime pickedUpAt, LocalDateTime droppedAt) {
        this.id = id;
        this.ride = ride;
        this.passenger = passenger;
        this.pickupLat = pickupLat;
        this.pickupLng = pickupLng;
        this.pickupAddress = pickupAddress;
        this.dropLat = dropLat;
        this.dropLng = dropLng;
        this.dropAddress = dropAddress;
        this.individualFare = individualFare;
        this.isPickedUp = isPickedUp;
        this.isDropped = isDropped;
        this.joinedAt = joinedAt;
        this.pickedUpAt = pickedUpAt;
        this.droppedAt = droppedAt;
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

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
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

    public Double getIndividualFare() {
        return individualFare;
    }

    public void setIndividualFare(Double individualFare) {
        this.individualFare = individualFare;
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        isPickedUp = pickedUp;
    }

    public boolean isDropped() {
        return isDropped;
    }

    public void setDropped(boolean dropped) {
        isDropped = dropped;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public LocalDateTime getPickedUpAt() {
        return pickedUpAt;
    }

    public void setPickedUpAt(LocalDateTime pickedUpAt) {
        this.pickedUpAt = pickedUpAt;
    }

    public LocalDateTime getDroppedAt() {
        return droppedAt;
    }

    public void setDroppedAt(LocalDateTime droppedAt) {
        this.droppedAt = droppedAt;
    }

    public static class Builder {
        private Long id;
        private Ride ride;
        private User passenger;
        private Double pickupLat;
        private Double pickupLng;
        private String pickupAddress;
        private Double dropLat;
        private Double dropLng;
        private String dropAddress;
        private Double individualFare;
        private boolean isPickedUp = false;
        private boolean isDropped = false;
        private LocalDateTime joinedAt;
        private LocalDateTime pickedUpAt;
        private LocalDateTime droppedAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder ride(Ride ride) {
            this.ride = ride;
            return this;
        }

        public Builder passenger(User passenger) {
            this.passenger = passenger;
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

        public Builder individualFare(Double individualFare) {
            this.individualFare = individualFare;
            return this;
        }

        public Builder isPickedUp(boolean isPickedUp) {
            this.isPickedUp = isPickedUp;
            return this;
        }

        public Builder isDropped(boolean isDropped) {
            this.isDropped = isDropped;
            return this;
        }

        public Builder joinedAt(LocalDateTime joinedAt) {
            this.joinedAt = joinedAt;
            return this;
        }

        public Builder pickedUpAt(LocalDateTime pickedUpAt) {
            this.pickedUpAt = pickedUpAt;
            return this;
        }

        public Builder droppedAt(LocalDateTime droppedAt) {
            this.droppedAt = droppedAt;
            return this;
        }

        public RidePassenger build() {
            return new RidePassenger(id, ride, passenger, pickupLat, pickupLng, pickupAddress, dropLat, dropLng,
                    dropAddress, individualFare, isPickedUp, isDropped, joinedAt, pickedUpAt, droppedAt);
        }
    }

}
