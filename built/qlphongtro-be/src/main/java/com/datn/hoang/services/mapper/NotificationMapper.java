package com.datn.hoang.services.mapper;

import com.datn.hoang.entity.Location;
import com.datn.hoang.entity.Notification;
import com.datn.hoang.services.dto.LocationDTO;
import com.datn.hoang.services.dto.NotificationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface NotificationMapper extends EntityMapper<NotificationDTO, Notification>{
}
