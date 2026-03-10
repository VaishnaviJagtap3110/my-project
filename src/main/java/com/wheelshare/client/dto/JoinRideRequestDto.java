package com.wheelshare.client.dto;

public class JoinRideRequestDto {
	private Long rideId;

    private Double pickupLat;
    private Double pickupLng;
    private String pickupAddress;

    private Double dropLat;
    private Double dropLng;
    private String dropAddress;

    private String message; // Optional message to ride owner

    public JoinRideRequestDto() {
    }

    public JoinRideRequestDto(Long rideId, Double pickupLat, Double pickupLng, String pickupAddress, Double dropLat,
            Double dropLng, String dropAddress, String message) {
        this.rideId = rideId;
        this.pickupLat = pickupLat;
        this.pickupLng = pickupLng;
        this.pickupAddress = pickupAddress;
        this.dropLat = dropLat;
        this.dropLng = dropLng;
        this.dropAddress = dropAddress;
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Builder {
        private Long rideId;
        private Double pickupLat;
        private Double pickupLng;
        private String pickupAddress;
        private Double dropLat;
        private Double dropLng;
        private String dropAddress;
        private String message;

        public Builder rideId(Long rideId) {
            this.rideId = rideId;
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

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public JoinRideRequestDto build() {
            return new JoinRideRequestDto(rideId, pickupLat, pickupLng, pickupAddress, dropLat, dropLng, dropAddress,
                    message);
        }
    }

}
