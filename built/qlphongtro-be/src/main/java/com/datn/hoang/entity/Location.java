package com.datn.hoang.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tbllocation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;


    @Column(name = "location_address")
    private String locationAddress;

    @Column(name = "location_x_coordinate")
    private Double locationXCoordinate;

    @Column(name = "location_y_coordinate")
    private Double locationYCoordinate;

    @Column(name = "location_ward_code")
    private String locationWardCode;

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
