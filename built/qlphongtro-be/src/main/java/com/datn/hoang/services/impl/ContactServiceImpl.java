package com.datn.hoang.services.impl;

import com.datn.hoang.entity.Contact;
import com.datn.hoang.repository.ContactRepository;
import com.datn.hoang.repository.WardRepository;
import com.datn.hoang.services.ContactService;
import com.datn.hoang.services.WardService;
import com.datn.hoang.services.dto.*;
import com.datn.hoang.services.mapper.ContactMapper;
import com.datn.hoang.services.mapper.WardsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public ServiceResult<ContactDTO> createContact(ContactDTO contactDTO){

        Contact newContact = contactMapper.toEntity(contactDTO);
        newContact.setContactCreatedDate(Instant.now());
        contactRepository.saveAndFlush(newContact);

        contactDTO.setContactId(newContact.getContactId());
        contactDTO.setContactCreatedDate(newContact.getContactCreatedDate());

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setMessage("Them moi lien he thanh cong");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(contactDTO);

        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> deleteContact(Long id){
        contactRepository.deleteContactByContactId(id);
        return new ServiceResult<>(true, HttpStatus.OK, "Xoa lien he thanh cong");
    }

    @Override
    public List<ContactDTO> getAllContact(){
        return contactRepository.findAll().stream().map(contactMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ServiceResult<Map<String, Object>> searchAllContactWithPagingation(SearchContactDTO searchOption){
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();

        try {
            //Tạo  size, page mặc định nếu trong searchOption không có
            Integer size = 5;
            Integer page = 0;

            if (searchOption.getSize() != null) {
                size = searchOption.getSize();
            }

            if (searchOption.getPage() != null) {
                page = searchOption.getPage();
                page--;
            }

            Pageable pageRequest = PageRequest.of(page, size);

            List<ContactDTO> pageUI = contactRepository.searchAllContactPagingation(pageRequest, searchOption).stream().map(contactMapper::toDto).collect(Collectors.toList());
            int total = contactRepository.countContact(searchOption);

            Map<String, Object> output = new HashMap<>();
            output.put("total", total);
            output.put("size", pageRequest.getPageSize());
            output.put("page", pageRequest.getPageNumber() + 1);
            output.put("contacts", pageUI);

            serviceResult.setMessage("Tim kiem thanh cong");
            serviceResult.setStatus(HttpStatus.OK);
            serviceResult.setData(output);
        } catch (Exception e) {
            serviceResult.setMessage("Tim kiem that bai");
            serviceResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResult;
    }

}
