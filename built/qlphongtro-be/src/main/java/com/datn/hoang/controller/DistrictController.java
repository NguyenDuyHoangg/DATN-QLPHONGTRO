package com.datn.hoang.controller;

import com.datn.hoang.repository.DistrictRepository;
import com.datn.hoang.services.DistrictService;
import com.datn.hoang.services.dto.DistrictsDTO;
import com.datn.hoang.services.dto.RoomTypeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/district")
public class DistrictController {
    private final Logger log = LoggerFactory.getLogger(DistrictController.class);

    private final DistrictService districtService;

    private final DistrictRepository districtRepository;

    public DistrictController(DistrictService districtService, DistrictRepository districtRepository) {
        this.districtService = districtService;
        this.districtRepository = districtRepository;
    }


    //Lấy tất cả quận
    @GetMapping("")
    public ResponseEntity<List<DistrictsDTO>> getAllDistrict() {
        return ResponseEntity.ok().body(districtService.getAllDistricts());
    }

}
