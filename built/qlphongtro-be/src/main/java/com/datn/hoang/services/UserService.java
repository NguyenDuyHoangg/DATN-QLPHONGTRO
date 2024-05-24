package com.datn.hoang.services;

import com.datn.hoang.services.dto.*;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDTO> getAllUser();

    UserDTO findUserByUserId(Long id);

    ServiceResult<UserDTO> create(UserDTO userDTO);

    ServiceResult<UserDTO> update(UserDTO userDTO);

    ServiceResult<Boolean> deleteUser(Long id);

    ServiceResult<UserDTO> authenticateUser(LoginDTO loginDTO);

    ServiceResult<Map<String, Object>> searchAllUserWithPagingation(SearchUserDTO searchOption);
}
