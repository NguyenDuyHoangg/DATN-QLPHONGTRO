package com.datn.hoang.services.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

public class RoomDTO {

    private Long roomId;

    private Double roomAcreage;

    private Double roomPrice;

    private Long roomStatus;

    private Double roomElectricPrice;

    private Double roomWaterPrice;

    private Long roomInternet;

    private Long roomParking;

    private Long roomAirconditioner;

    private Long roomHeater;

    private Long roomTelevision;

    private Long roomToilet;

    private Double roomInternetPrice;

    private Double roomEnviromentPrice;

    private String roomNotes;

    private String roomHostName;

    private String roomHostPhone;

    private String roomHostZalo;

    private String roomHostMXH;

    private Long roomRoomTypeId;

    private Long roomLocationId;

    private LocationDTO locationDTO;

    private ArticleDTO articleDTO;


    public RoomDTO() {
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    public ArticleDTO getArticleDTO() {
        return articleDTO;
    }

    public void setArticleDTO(ArticleDTO articleDTO) {
        this.articleDTO = articleDTO;
    }

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
