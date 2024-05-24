package com.datn.hoang.controller;

import com.datn.hoang.repository.WardRepository;
import com.datn.hoang.services.ContactService;
import com.datn.hoang.services.WardService;
import com.datn.hoang.services.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("")
    public ResponseEntity getAllContact(){
        return ResponseEntity.ok().body(contactService.getAllContact());
    }

    @PostMapping("/create")
    public ResponseEntity createContact(@RequestBody(required = false) ContactDTO contactDTO){
        return ResponseEntity.ok().body(contactService.createContact(contactDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContact(@PathVariable Long id){
        return ResponseEntity.ok().body(contactService.deleteContact(id));
    }

    //Search với phân trang
    @PostMapping(path = "/search")
    public ResponseEntity<ServiceResult> SearchContactWithPagination(@RequestBody SearchContactDTO searchOption) {
        ServiceResult<Map<String, Object>> result = contactService.searchAllContactWithPagingation(searchOption);
        return ResponseEntity.ok().body(result);
    }

}
