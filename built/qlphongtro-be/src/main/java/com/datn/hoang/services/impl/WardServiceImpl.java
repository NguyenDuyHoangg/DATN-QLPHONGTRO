package com.datn.hoang.services.impl;

import com.datn.hoang.repository.WardRepository;
import com.datn.hoang.services.WardService;
import com.datn.hoang.services.dto.DistrictsDTO;
import com.datn.hoang.services.dto.WardsDTO;
import com.datn.hoang.services.mapper.WardsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WardServiceImpl implements WardService {

    private final Logger log = LoggerFactory.getLogger(WardServiceImpl.class);

    private final WardsMapper wardsMapper;

    private final WardRepository wardRepository;

    public WardServiceImpl(WardsMapper wardsMapper, WardRepository wardRepository) {
        this.wardsMapper = wardsMapper;
        this.wardRepository = wardRepository;
    }


    public List<WardsDTO> getAllWards(String districtCode){
        return wardRepository.findWardsByDistrictCode(districtCode).stream().map(wardsMapper::toDto).collect(Collectors.toList());
    }
}
