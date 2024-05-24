package com.datn.hoang.services;

import com.datn.hoang.services.dto.DistrictsDTO;
import com.datn.hoang.services.dto.RoomTypeDTO;

import java.util.List;

public interface DistrictService {
    List<DistrictsDTO> getAllDistricts();
}
