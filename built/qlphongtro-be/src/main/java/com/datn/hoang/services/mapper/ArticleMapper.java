package com.datn.hoang.services.mapper;

import com.datn.hoang.entity.*;
import com.datn.hoang.services.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ArticleMapper extends EntityMapper<ArticleDTO, Article>{
}
