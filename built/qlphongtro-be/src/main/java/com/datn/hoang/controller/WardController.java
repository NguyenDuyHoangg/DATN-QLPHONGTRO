package com.datn.hoang.controller;

import com.datn.hoang.repository.WardRepository;
import com.datn.hoang.services.WardService;
import com.datn.hoang.services.dto.DistrictsDTO;
import com.datn.hoang.services.dto.WardsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ward")
public class WardController {
    private final Logger log = LoggerFactory.getLogger(WardController.class);

    private final WardService wardService;

    private final WardRepository wardRepository;

    public WardController(WardService wardService, WardRepository wardRepository) {
        this.wardService = wardService;
        this.wardRepository = wardRepository;
    }


    //Lấy tất cả quận
    @GetMapping("")
    public ResponseEntity<List<WardsDTO>> getAllWard(@RequestParam(required = false) String districtCode) {
        return ResponseEntity.ok().body(wardService.getAllWards(districtCode));
    }

}
