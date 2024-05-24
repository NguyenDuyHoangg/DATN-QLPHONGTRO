package com.datn.hoang.services.mapper;

import com.datn.hoang.entity.Article;
import com.datn.hoang.entity.Image;
import com.datn.hoang.services.dto.ArticleDTO;
import com.datn.hoang.services.dto.ImageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ImageMapper extends EntityMapper<ImageDTO, Image>{
}
