package com.datn.hoang.services.mapper;

import com.datn.hoang.entity.Image;
import com.datn.hoang.entity.Location;
import com.datn.hoang.services.dto.ImageDTO;
import com.datn.hoang.services.dto.LocationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface LocationMapper extends EntityMapper<LocationDTO, Location>{
}
