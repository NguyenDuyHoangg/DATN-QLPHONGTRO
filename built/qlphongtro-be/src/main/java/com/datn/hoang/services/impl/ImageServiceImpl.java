package com.datn.hoang.services.impl;

import com.datn.hoang.entity.Image;
import com.datn.hoang.entity.User;
import com.datn.hoang.repository.ArticleRepository;
import com.datn.hoang.repository.ImageRepository;
import com.datn.hoang.repository.UserRepository;
import com.datn.hoang.services.FileService;
import com.datn.hoang.services.ImageService;
import com.datn.hoang.services.dto.FileDto;
import com.datn.hoang.services.dto.ImageDTO;
import com.datn.hoang.services.dto.ServiceResult;
import com.datn.hoang.services.dto.UserDTO;
import com.datn.hoang.services.mapper.ImageMapper;
import com.datn.hoang.services.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final ImageMapper imageMapper;

    private final FileService fileService;

    public ImageServiceImpl(ImageRepository imageRepository, ArticleRepository articleRepository, UserRepository userRepository, UserMapper userMapper, ImageMapper imageMapper, FileService fileService) {
        this.imageRepository = imageRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.imageMapper = imageMapper;
        this.fileService = fileService;
    }

    @Override
    public Image getImage(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow();
    }

    @Override
    public List<ImageDTO> getImageByArticleId(Long articleId) {
        return imageRepository.findImageByImageArticleId(articleId).stream().map(imageMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ServiceResult<List<ImageDTO>> uploadFiles(List<MultipartFile> multipartFiles, Long id){
        if(multipartFiles!=null){
            ServiceResult<List<FileDto>> lstFile = fileService.createListFile(multipartFiles);
            List<Image> lstImage = new ArrayList<>();
            for (FileDto item : lstFile.getData()){
                Image img = new Image();
                img.setImageUrl(item.getFileName());
                img.setImageArticleId(id);
                lstImage.add(img);
            }
            lstImage = imageRepository.saveAll(lstImage);
            List<ImageDTO> lstImageDTO = lstImage.stream().map(imageMapper::toDto).collect(Collectors.toList());
            return new ServiceResult<>(lstImageDTO, HttpStatus.OK, "Luu files anh thanh cong");
        }
        return new ServiceResult<>(null, HttpStatus.BAD_REQUEST, "Luu files that bai");
    }

    @Override
    public ServiceResult<UserDTO> uploadFile(MultipartFile multipartFile, Long id){
        if(multipartFile!=null){
            ServiceResult<FileDto> file = fileService.createFile(multipartFile);
            User u = userRepository.findUserByUserId(id);
            u.setUserImage(file.getData().getFileName());
            userRepository.saveAndFlush(u);
            UserDTO userDTO = userMapper.toDto(u);
            return new ServiceResult<>(userDTO, HttpStatus.OK, "Luu file anh nguoi dung thanh cong");
        }
        return new ServiceResult<>(null, HttpStatus.BAD_REQUEST, "Luu file anh nguoi dung that bai");
    }
}
