package com.wheelshare.client.dto;

public class SharedRideRequestDto {
	private Double pickupLat;
    private Double pickupLng;
    private String pickupAddress;

    private Double dropLat;
    private Double dropLng;
    private String dropAddress;

    private Integer maxPassengers;
    private Boolean isShareable;
    private String rideType; // "INDIVIDUAL", "SHARED", "POOL"

    public SharedRideRequestDto() {
    }

    public SharedRideRequestDto(Double pickupLat, Double pickupLng, String pickupAddress, Double dropLat,
            Double dropLng, String dropAddress, Integer maxPassengers, Boolean isShareable, String rideType) {
        this.pickupLat = pickupLat;
        this.pickupLng = pickupLng;
        this.pickupAddress = pickupAddress;
        this.dropLat = dropLat;
        this.dropLng = dropLng;
        this.dropAddress = dropAddress;
        this.maxPassengers = maxPassengers;
        this.isShareable = isShareable;
        this.rideType = rideType;
    }

    public static Builder builder() {
        return new Builder();
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

    public Integer getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(Integer maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public Boolean getIsShareable() {
        return isShareable;
    }

    public void setIsShareable(Boolean isShareable) {
        this.isShareable = isShareable;
    }

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public static class Builder {
        private Double pickupLat;
        private Double pickupLng;
        private String pickupAddress;
        private Double dropLat;
        private Double dropLng;
        private String dropAddress;
        private Integer maxPassengers;
        private Boolean isShareable;
        private String rideType;

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

        public Builder maxPassengers(Integer maxPassengers) {
            this.maxPassengers = maxPassengers;
            return this;
        }

        public Builder isShareable(Boolean isShareable) {
            this.isShareable = isShareable;
            return this;
        }

        public Builder rideType(String rideType) {
            this.rideType = rideType;
            return this;
        }

        public SharedRideRequestDto build() {
            return new SharedRideRequestDto(pickupLat, pickupLng, pickupAddress, dropLat, dropLng, dropAddress,
                    maxPassengers, isShareable, rideType);
        }
    }

}
