package com.datn.hoang.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tblarea")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long areaId;

    @Column(name = "area_city")
    private String areaCity;

    @Column(name = "area_district")
    private String areaDistrict;

    @Column(name = "area_wards")
    private String areaWards;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaCity() {
        return areaCity;
    }

    public void setAreaCity(String areaCity) {
        this.areaCity = areaCity;
    }

    public String getAreaDistrict() {
        return areaDistrict;
    }

    public void setAreaDistrict(String areaDistrict) {
        this.areaDistrict = areaDistrict;
    }

    public String getAreaWards() {
        return areaWards;
    }

    public void setAreaWards(String areaWards) {
        this.areaWards = areaWards;
    }
}
