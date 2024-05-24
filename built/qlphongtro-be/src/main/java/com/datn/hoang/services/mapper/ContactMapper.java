package com.datn.hoang.services.mapper;

import com.datn.hoang.entity.Contact;
import com.datn.hoang.services.dto.ContactDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ContactMapper extends EntityMapper<ContactDTO, Contact>{
}
