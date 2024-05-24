package com.datn.hoang.controller;

import com.datn.hoang.repository.UserRepository;
import com.datn.hoang.services.UserService;
import com.datn.hoang.services.dto.*;
import com.datn.hoang.services.impl.UserServiceImpl;
import com.datn.hoang.services.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    private final UserService userService;

    public UserController(UserRepository userRepository, UserMapper userMapper, UserService userService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        log.debug("REST request to get all user");
        List<UserDTO> result = userService.getAllUser();
        return ResponseEntity.ok().body(result);
    }

    //Search với phân trang
    @PostMapping(path = "/search")
    public ResponseEntity<ServiceResult> SearchUserWithPagination(@RequestBody SearchUserDTO searchOption) {
        log.debug("REST request to search User by Searchoption: {}");
        ServiceResult<Map<String, Object>> result = userService.searchAllUserWithPagingation(searchOption);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        log.debug("REST request to get user by id");
        return ResponseEntity.ok().body(userService.findUserByUserId(id));
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody(required = false) UserDTO user){
        return ResponseEntity.ok().body(userService.create(user));
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody(required = false) UserDTO user){
        return ResponseEntity.ok().body(userService.update(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody(required = false) LoginDTO loginDTO){
        return ResponseEntity.ok().body(userService.authenticateUser(loginDTO));
    }

    @GetMapping(path = "/count/guest")
    public ResponseEntity<ServiceResult> countGuest(){
        int total = userRepository.countGuest();
        Map<String, Object> output = new HashMap<>();
        output.put("total", total);
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();
        serviceResult.setMessage("lay tong so khach hang thanh cong");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(output);
        return ResponseEntity.ok().body(serviceResult);
    }

    @GetMapping(path = "/count/model")
    public ResponseEntity<ServiceResult> countModel(){
        int total = userRepository.countModel();
        Map<String, Object> output = new HashMap<>();
        output.put("total", total);
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();
        serviceResult.setMessage("lay tong so chu tro thanh cong");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(output);
        return ResponseEntity.ok().body(serviceResult);
    }

    @GetMapping(path = "/count/user")
    public ResponseEntity<ServiceResult> countUserNotAdmin(){
        int total = userRepository.countUserNotAdmin();
        Map<String, Object> output = new HashMap<>();
        output.put("total", total);
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();
        serviceResult.setMessage("lay tong so nguoi dung thanh cong");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(output);
        return ResponseEntity.ok().body(serviceResult);
    }

}
