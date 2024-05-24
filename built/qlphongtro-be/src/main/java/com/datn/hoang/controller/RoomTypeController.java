package com.datn.hoang.controller;

import com.datn.hoang.entity.RoomType;
import com.datn.hoang.repository.RoomTypeRepository;
import com.datn.hoang.services.RoomTypeService;
import com.datn.hoang.services.dto.ArticleDTO;
import com.datn.hoang.services.dto.RoomTypeDTO;
import com.datn.hoang.services.dto.SearchDTO;
import com.datn.hoang.services.dto.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/room-type")
public class RoomTypeController {
    private final Logger log = LoggerFactory.getLogger(RoomTypeController.class);

    private final RoomTypeService roomTypeService;

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeController(RoomTypeService roomTypeService, RoomTypeRepository roomTypeRepository) {
        this.roomTypeService = roomTypeService;
        this.roomTypeRepository = roomTypeRepository;
    }


    //Lấy tất cả loại phòng
    @GetMapping("")
    public ResponseEntity<List<RoomTypeDTO>> getAllRoomType() {
        return ResponseEntity.ok().body(roomTypeService.getAllRoomType());
    }

}
