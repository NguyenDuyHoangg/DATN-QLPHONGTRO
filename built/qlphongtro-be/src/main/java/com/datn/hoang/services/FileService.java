package com.datn.hoang.services;


import com.datn.hoang.services.dto.FileDto;
import com.datn.hoang.services.dto.ServiceResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    public ServiceResult<FileDto> createFile(MultipartFile file);
    public ServiceResult<List<FileDto>> createListFile(List<MultipartFile> file);
    public ServiceResult<?> deleteFileByName(String fileNameToDelete) throws IOException;


}
