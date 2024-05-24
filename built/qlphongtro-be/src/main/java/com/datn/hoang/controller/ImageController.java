package com.datn.hoang.controller;

import com.datn.hoang.repository.ImageRepository;
import com.datn.hoang.services.ImageService;
import com.datn.hoang.services.dto.ImageDTO;
import com.datn.hoang.services.dto.ServiceResult;
import com.datn.hoang.services.dto.WardsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/image")
public class ImageController {
    private final Logger log = LoggerFactory.getLogger(ImageController.class);

    private final ImageService imageService;

    private final ImageRepository imageRepository;

    public ImageController(ImageService imageService, ImageRepository imageRepository) {
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }

    //Lấy tất cả ảnh theo article_id
    @GetMapping("/{id}")
    public ResponseEntity<List<ImageDTO>> getAllImage(@PathVariable Long id) {
        return ResponseEntity.ok().body(imageService.getImageByArticleId(id));
    }

    //Uploads file
    @PostMapping("/uploads/{id}")
    public ResponseEntity<ServiceResult> uploadImages(@RequestPart("fileUpload") List<MultipartFile> multipartFiles, @PathVariable Long id){
        return ResponseEntity.ok().body(imageService.uploadFiles(multipartFiles, id));
    }

    //Uploads file
    @PostMapping("/upload/{id}")
    public ResponseEntity<ServiceResult> uploadImage(@RequestPart("fileUploadUser") MultipartFile multipartFiles, @PathVariable Long id){
        return ResponseEntity.ok().body(imageService.uploadFile(multipartFiles,id));
    }

}
