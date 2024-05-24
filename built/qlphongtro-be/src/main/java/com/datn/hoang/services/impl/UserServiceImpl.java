package com.datn.hoang.services.impl;

import com.datn.hoang.entity.User;
import com.datn.hoang.repository.ArticleRepository;
import com.datn.hoang.repository.UserRepository;
import com.datn.hoang.services.UserService;
import com.datn.hoang.services.dto.LoginDTO;
import com.datn.hoang.services.dto.SearchUserDTO;
import com.datn.hoang.services.dto.ServiceResult;
import com.datn.hoang.services.dto.UserDTO;
import com.datn.hoang.services.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final ArticleRepository articleRepository;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, ArticleRepository articleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.userMapper = userMapper;
    }
    @Override
    public List<UserDTO> getAllUser(){
        return userRepository.getAllUser().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserByUserId(Long id){
        return userMapper.toDto(userRepository.findUserByUserId(id));
    }

    @Override
    public ServiceResult<UserDTO> create(UserDTO userDTO){
        List<User> lstUser = userRepository.getAllUser();
        String messageErr = "";

        //Kiem tra ten dang nhap da ton tai chua
        if(userRepository.existsUserByUserName(userDTO.getUserName())){
            messageErr += "Ten dang nhap da ton tai!";
        }

        //Kiem tra email da ton tai chua
        if(userRepository.existsUserByUserEmail(userDTO.getUserEmail())){
            messageErr += "Email nguoi dung da ton tai!";
        }

        //Kiem tra sdt da ton tai chua
        if(userRepository.existsUserByUserPhone(userDTO.getUserPhone())){
            messageErr += "So dien thoai nguoi dung da ton tai!";
        }

        if (messageErr.length() > 0) {
            String mess = messageErr.replace(".", ", ");
            mess = mess.substring(0, mess.length() - 1);
            return new ServiceResult<>(null, HttpStatus.NOT_MODIFIED, mess);
        }

        //Thoa man cac dieu kien
        User newUser = userMapper.toEntity(userDTO);
        newUser.setUserRepass(userDTO.getUserRepass());
        newUser = userRepository.saveAndFlush(newUser);

        ServiceResult serviceResult = new ServiceResult<List<Map<String, Object>>>();
        serviceResult.setMessage("Them moi nguoi dung thanh cong");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(userDTO);

        return serviceResult;
    }

    @Override
    public ServiceResult<UserDTO> update(UserDTO userDTO){
        String messageErr = "";

        User user = userRepository.findUserByUserId(userDTO.getUserId());
        if(user == null){
            messageErr += "Không tìm thấy người dùng";
        }

        //Kiem tra email da ton tai chua
        User uMail = userRepository.findUserByUserEmail(userDTO.getUserEmail());
        if(uMail != null && uMail.getUserId() != userDTO.getUserId()){
            messageErr += "Email nguoi dung da ton tai!";
        }

        //Kiem tra sdt da ton tai chua
        User uPhone = userRepository.findUserByUserPhone(userDTO.getUserPhone());
        if(uPhone != null && uPhone.getUserId() != userDTO.getUserId()){
            messageErr += "So dien thoai nguoi dung da ton tai!";
        }

        if (messageErr.length() > 0) {
            String mess = messageErr.replace(".", ", ");
            mess = mess.substring(0, mess.length() - 1);
            return new ServiceResult<>(null, HttpStatus.NOT_MODIFIED, mess);
        }

        //Thoa man cac dieu kien
        User updateUser = userMapper.toEntity(userDTO);
        updateUser.setUserRepass(userDTO.getUserRepass());
        userRepository.saveAndFlush(updateUser);

        ServiceResult serviceResult = new ServiceResult<List<Map<String, Object>>>();
        serviceResult.setMessage("Cap nhat nguoi dung thanh cong");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(userDTO);

        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> deleteUser(Long id){
        User user = userRepository.findUserByUserId(id);

        if(articleRepository.existsArticlesByArticleUserId(id)){
            return new ServiceResult<>(false, HttpStatus.BAD_REQUEST, "Khong xoa nguoi dung vi ton tai bai dang!");
        }

        userRepository.deleteById(id);

        return new ServiceResult<>(true, HttpStatus.OK, "Xoa nguoi dung thanh cong");
    }

    @Override
    public ServiceResult<UserDTO> authenticateUser(LoginDTO loginDTO){
        UserDTO userDTO = userMapper.toDto(userRepository.findUserByUserNameAndUserPass(loginDTO.getUserName(), loginDTO.getUserPass()));
        if(userDTO != null){
            Long roles = userDTO.getUserRoles();
            if(roles==0 || roles==1 || roles==2){
                User u = userRepository.findUserByUserId(userDTO.getUserId());
                u.setUserLastLogined(new Date());
                userRepository.saveAndFlush(u);
                userDTO.setUserLastLogined(u.getUserLastLogined());
                return new ServiceResult<>(userDTO, HttpStatus.OK, "Đăng nhập thành công!");
            }else {
                return new ServiceResult<>(null, HttpStatus.FORBIDDEN, "Quyền truy cập không hợp lệ!");
            }
        }else {
            return new ServiceResult<>(null, HttpStatus.UNAUTHORIZED, "Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng nhập lại!");
        }
    }

    @Override
    public ServiceResult<Map<String, Object>> searchAllUserWithPagingation(SearchUserDTO searchOption){
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

            // Xử lý lại các otption trước khi tìm kiếm
            searchOption.handleBeforeSearch();

            List<UserDTO> pageUI = userRepository.searchAllUserWithPagingation(pageRequest, searchOption).stream().map(userMapper::toDto).collect(Collectors.toList());
            int total = userRepository.countUser(searchOption);

            Map<String, Object> output = new HashMap<>();
            output.put("total", total);
            output.put("size", pageRequest.getPageSize());
            output.put("page", pageRequest.getPageNumber() + 1);
            output.put("users", pageUI);

            serviceResult.setMessage("Tim kiem thanh cong");
            serviceResult.setStatus(HttpStatus.OK);
            serviceResult.setData(output);
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
            serviceResult.setMessage("Tim kiem that bai");
            serviceResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResult;
    }
}
