package com.datn.hoang.services.dto;

import jakarta.persistence.Column;
import lombok.*;
public class RoomTypeDTO {
    private Long roomTypeId;

    private String roomTypeName;

    public RoomTypeDTO() {
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
}
