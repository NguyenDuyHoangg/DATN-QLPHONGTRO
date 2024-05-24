package com.datn.hoang.services.mapper;

import com.datn.hoang.entity.Notification;
import com.datn.hoang.entity.Provinces;
import com.datn.hoang.services.dto.NotificationDTO;
import com.datn.hoang.services.dto.ProvincesDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProvincesMapper extends EntityMapper<ProvincesDTO, Provinces>{
}
