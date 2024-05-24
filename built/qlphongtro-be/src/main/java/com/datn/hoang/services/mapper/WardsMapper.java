package com.datn.hoang.services.mapper;

import com.datn.hoang.entity.User;
import com.datn.hoang.entity.Wards;
import com.datn.hoang.services.dto.UserDTO;
import com.datn.hoang.services.dto.WardsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface WardsMapper extends EntityMapper<WardsDTO, Wards>{
}
