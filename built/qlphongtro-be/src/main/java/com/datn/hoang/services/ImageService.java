package com.datn.hoang.services;

import com.datn.hoang.entity.Image;
import com.datn.hoang.services.dto.ImageDTO;
import com.datn.hoang.services.dto.ServiceResult;
import com.datn.hoang.services.dto.UserDTO;
import com.datn.hoang.services.dto.WardsDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image getImage(Long imageId);
    List<ImageDTO> getImageByArticleId(Long articleId);

    ServiceResult<List<ImageDTO>> uploadFiles(List<MultipartFile> multipartFiles, Long id);

    ServiceResult<UserDTO> uploadFile(MultipartFile multipartFile, Long id);
}
