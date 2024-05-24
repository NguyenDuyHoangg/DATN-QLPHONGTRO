package com.datn.hoang.services.mapper;

import com.datn.hoang.entity.User;
import com.datn.hoang.services.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User>{
}
