package com.datn.hoang.services.mapper;

import com.datn.hoang.entity.Room;
import com.datn.hoang.services.dto.RoomDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface RoomMapper extends EntityMapper<RoomDTO, Room>{
}
