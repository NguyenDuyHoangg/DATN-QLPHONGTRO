package com.datn.hoang.services.dto;

import jakarta.persistence.Column;
import lombok.*;

public class LocationDTO {
    private Long locationId;

    private String locationAddress;

    private Double locationXCoordinate;

    private Double locationYCoordinate;

    private String locationWardCode;

    public LocationDTO() {
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public Double getLocationXCoordinate() {
        return locationXCoordinate;
    }

    public void setLocationXCoordinate(Double locationXCoordinate) {
        this.locationXCoordinate = locationXCoordinate;
    }

    public Double getLocationYCoordinate() {
        return locationYCoordinate;
    }

    public void setLocationYCoordinate(Double locationYCoordinate) {
        this.locationYCoordinate = locationYCoordinate;
    }

    public String getLocationWardCode() {
        return locationWardCode;
    }

    public void setLocationWardCode(String locationWardCode) {
        this.locationWardCode = locationWardCode;
    }
}
