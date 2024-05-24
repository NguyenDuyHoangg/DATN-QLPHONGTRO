package com.datn.hoang.services;

import com.datn.hoang.entity.RoomType;
import com.datn.hoang.services.dto.LoginDTO;
import com.datn.hoang.services.dto.RoomTypeDTO;
import com.datn.hoang.services.dto.ServiceResult;
import com.datn.hoang.services.dto.UserDTO;

import java.util.List;

public interface RoomTypeService {
    List<RoomTypeDTO> getAllRoomType();
}
