package com.datn.hoang.services;

import com.datn.hoang.services.dto.*;

import java.util.List;
import java.util.Map;

public interface ContactService {

    List<ContactDTO> getAllContact();
    ServiceResult<ContactDTO> createContact(ContactDTO contactDTO);

    ServiceResult<Boolean> deleteContact(Long id);

    ServiceResult<Map<String, Object>> searchAllContactWithPagingation(SearchContactDTO searchOption);
}
