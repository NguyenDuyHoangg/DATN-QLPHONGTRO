package com.datn.hoang.services.dto;

import jakarta.persistence.Column;

public class DistrictsDTO {
    public String code;

    public String name;

    public String nameEn;

    public String fullName;

    public String fullNameEn;

    public String provinceCode;

    public DistrictsDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
