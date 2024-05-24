package com.datn.hoang.services.impl;

import com.datn.hoang.entity.Article;
import com.datn.hoang.entity.RoomType;
import com.datn.hoang.repository.RoomTypeRepository;
import com.datn.hoang.services.RoomTypeService;
import com.datn.hoang.services.dto.ArticleDTO;
import com.datn.hoang.services.dto.RoomTypeDTO;
import com.datn.hoang.services.dto.SearchDTO;
import com.datn.hoang.services.dto.ServiceResult;
import com.datn.hoang.services.mapper.RoomTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    private final Logger log = LoggerFactory.getLogger(RoomTypeServiceImpl.class);

    private final RoomTypeMapper roomTypeMapper;

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeServiceImpl(RoomTypeMapper roomTypeMapper, RoomTypeRepository roomTypeRepository) {
        this.roomTypeMapper = roomTypeMapper;
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomTypeDTO> getAllRoomType(){
        return roomTypeRepository.findAll().stream().map(roomTypeMapper::toDto).collect(Collectors.toList());
    }
}
