package com.datn.hoang.services.impl;

import com.datn.hoang.repository.DistrictRepository;
import com.datn.hoang.repository.RoomTypeRepository;
import com.datn.hoang.services.DistrictService;
import com.datn.hoang.services.RoomTypeService;
import com.datn.hoang.services.dto.DistrictsDTO;
import com.datn.hoang.services.dto.RoomTypeDTO;
import com.datn.hoang.services.mapper.DistrictsMapper;
import com.datn.hoang.services.mapper.RoomTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    private final Logger log = LoggerFactory.getLogger(DistrictServiceImpl.class);

    private final DistrictsMapper districtsMapper;

    private final DistrictRepository districtRepository;

    public DistrictServiceImpl(DistrictsMapper districtsMapper, DistrictRepository districtRepository) {
        this.districtsMapper = districtsMapper;
        this.districtRepository = districtRepository;
    }

    public List<DistrictsDTO> getAllDistricts(){
        return districtRepository.findAll().stream().map(districtsMapper::toDto).collect(Collectors.toList());
    }
}
