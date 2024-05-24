package com.datn.hoang.services.mapper;

import com.datn.hoang.entity.Notification;
import com.datn.hoang.entity.RoomType;
import com.datn.hoang.services.dto.NotificationDTO;
import com.datn.hoang.services.dto.RoomTypeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface RoomTypeMapper extends EntityMapper<RoomTypeDTO, RoomType>{
}
