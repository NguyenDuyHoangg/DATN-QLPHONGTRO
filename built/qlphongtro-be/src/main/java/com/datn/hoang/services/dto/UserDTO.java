package com.datn.hoang.services.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

public class UserDTO {
    private Long userId;

    private String userName;

    private String userPass;

    private String userRepass;

    private String userFullName;

    private Date userBirthday;

    private String userPhone;

    private String userZalo;

    private String userAddress;

    private String userEmail;

    private Date userLastLogined;

    private Long userTrash;

    private String userImage;

    private Long userRoles;

    public UserDTO() {
    }

    public String getUserRepass() {
        return userRepass;
    }

    public void setUserRepass(String userRepass) {
        this.userRepass = userRepass;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserZalo() {
        return userZalo;
    }

    public void setUserZalo(String userZalo) {
        this.userZalo = userZalo;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Long userRoles) {
        this.userRoles = userRoles;
    }

    public Date getUserLastLogined() {
        return userLastLogined;
    }

    public void setUserLastLogined(Date userLastLogined) {
        this.userLastLogined = userLastLogined;
    }

    public Long getUserTrash() {
        return userTrash;
    }

    public void setUserTrash(Long userTrash) {
        this.userTrash = userTrash;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
