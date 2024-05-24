package com.datn.hoang.services;

import com.datn.hoang.services.dto.DistrictsDTO;
import com.datn.hoang.services.dto.WardsDTO;

import java.util.List;

public interface WardService {
    List<WardsDTO> getAllWards(String districtCode);
}
