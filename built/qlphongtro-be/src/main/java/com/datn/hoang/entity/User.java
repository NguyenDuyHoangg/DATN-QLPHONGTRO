package com.datn.hoang.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbluser")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pass")
    private String userPass;

    @Column(name = "user_repass")
    private String userRepass;

    @Column(name = "user_fullname")
    private String userFullName;

    @Column(name = "user_birthday")
    private Date userBirthday;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_zalo")
    private String userZalo;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_last_logined")
    private Date userLastLogined;

    @Column(name = "user_trash")
    private Long userTrash;

    @Column(name = "user_image")
    private String userImage;

    @Column(name = "user_roles")
    private Long userRoles;

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

    public Long getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Long userRoles) {
        this.userRoles = userRoles;
    }
}
