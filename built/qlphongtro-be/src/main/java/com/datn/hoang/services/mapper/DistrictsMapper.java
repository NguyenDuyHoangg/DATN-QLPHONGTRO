package com.datn.hoang.services.mapper;

import com.datn.hoang.entity.Contact;
import com.datn.hoang.entity.Districts;
import com.datn.hoang.services.dto.ContactDTO;
import com.datn.hoang.services.dto.DistrictsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface DistrictsMapper extends EntityMapper<DistrictsDTO, Districts>{
}
