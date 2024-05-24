package com.datn.hoang.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tblroom")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_acreage")
    private Double roomAcreage;

    @Column(name = "room_price")
    private Double roomPrice;

    @Column(name = "room_status")
    private Long roomStatus;

    @Column(name = "room_electric_price")
    private Double roomElectricPrice;

    @Column(name = "room_water_price")
    private Double roomWaterPrice;

    @Column(name = "room_internet")
    private Long roomInternet;

    @Column(name = "room_parking")
    private Long roomParking;

    @Column(name = "room_airconditioner")
    private Long roomAirconditioner;

    @Column(name = "room_heater")
    private Long roomHeater;

    @Column(name = "room_television")
    private Long roomTelevision;

    @Column(name = "room_toilet")
    private Long roomToilet;

    @Column(name = "room_internet_price")
    private Double roomInternetPrice;

    @Column(name = "room_enviroment_price")
    private Double roomEnviromentPrice;

    @Column(name = "room_notes")
    private String roomNotes;

    @Column(name = "room_host_name")
    private String roomHostName;

    @Column(name = "room_host_phone")
    private String roomHostPhone;

    @Column(name = "room_host_zalo")
    private String roomHostZalo;

    @Column(name = "room_host_mxh")
    private String roomHostMXH;

    @Column(name = "room_roomType_id")
    private Long roomRoomTypeId;

    @Column(name = "room_location_id")
    private Long roomLocationId;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Double getRoomAcreage() {
        return roomAcreage;
    }

    public void setRoomAcreage(Double roomAcreage) {
        this.roomAcreage = roomAcreage;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Long getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Long roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Double getRoomElectricPrice() {
        return roomElectricPrice;
    }

    public void setRoomElectricPrice(Double roomElectricPrice) {
        this.roomElectricPrice = roomElectricPrice;
    }

    public Double getRoomWaterPrice() {
        return roomWaterPrice;
    }

    public void setRoomWaterPrice(Double roomWaterPrice) {
        this.roomWaterPrice = roomWaterPrice;
    }

    public Long getRoomInternet() {
        return roomInternet;
    }

    public void setRoomInternet(Long roomInternet) {
        this.roomInternet = roomInternet;
    }

    public Long getRoomParking() {
        return roomParking;
    }

    public void setRoomParking(Long roomParking) {
        this.roomParking = roomParking;
    }

    public Long getRoomAirconditioner() {
        return roomAirconditioner;
    }

    public void setRoomAirconditioner(Long roomAirconditioner) {
        this.roomAirconditioner = roomAirconditioner;
    }

    public Long getRoomHeater() {
        return roomHeater;
    }

    public void setRoomHeater(Long roomHeater) {
        this.roomHeater = roomHeater;
    }

    public Long getRoomTelevision() {
        return roomTelevision;
    }

    public void setRoomTelevision(Long roomTelevision) {
        this.roomTelevision = roomTelevision;
    }

    public Long getRoomToilet() {
        return roomToilet;
    }

    public void setRoomToilet(Long roomToilet) {
        this.roomToilet = roomToilet;
    }

    public Double getRoomInternetPrice() {
        return roomInternetPrice;
    }

    public void setRoomInternetPrice(Double roomInternetPrice) {
        this.roomInternetPrice = roomInternetPrice;
    }

    public Double getRoomEnviromentPrice() {
        return roomEnviromentPrice;
    }

    public void setRoomEnviromentPrice(Double roomEnviromentPrice) {
        this.roomEnviromentPrice = roomEnviromentPrice;
    }

    public String getRoomNotes() {
        return roomNotes;
    }

    public void setRoomNotes(String roomNotes) {
        this.roomNotes = roomNotes;
    }

    public String getRoomHostName() {
        return roomHostName;
    }

    public void setRoomHostName(String roomHostName) {
        this.roomHostName = roomHostName;
    }

    public String getRoomHostPhone() {
        return roomHostPhone;
    }

    public void setRoomHostPhone(String roomHostPhone) {
        this.roomHostPhone = roomHostPhone;
    }

    public String getRoomHostZalo() {
        return roomHostZalo;
    }

    public void setRoomHostZalo(String roomHostZalo) {
        this.roomHostZalo = roomHostZalo;
    }

    public String getRoomHostMXH() {
        return roomHostMXH;
    }

    public void setRoomHostMXH(String roomHostMXH) {
        this.roomHostMXH = roomHostMXH;
    }

    public Long getRoomRoomTypeId() {
        return roomRoomTypeId;
    }

    public void setRoomRoomTypeId(Long roomRoomTypeId) {
        this.roomRoomTypeId = roomRoomTypeId;
    }

    public Long getRoomLocationId() {
        return roomLocationId;
    }

    public void setRoomLocationId(Long roomLocationId) {
        this.roomLocationId = roomLocationId;
    }
}
