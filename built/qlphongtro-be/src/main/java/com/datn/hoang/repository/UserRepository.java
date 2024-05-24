package com.datn.hoang.repository;

import com.datn.hoang.entity.Article;
import com.datn.hoang.entity.User;
import com.datn.hoang.services.dto.SearchDTO;
import com.datn.hoang.services.dto.SearchUserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM tbluser u WHERE u.user_trash = 0 AND u.user_roles = 1 OR u.user_roles = 2",
    nativeQuery = true)
    //Lấy tất cả khách hàng chưa xóa và có quyền đăng nhập là khách hàng và chủ trọ
    List<User> getAllUser();

    @Query(value = "SELECT * FROM tbluser u WHERE (u.user_trash = 0 AND (u.user_roles = 1 OR u.user_roles = 2)) " +
            "AND ((LOWER(u.user_name) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, u.user_name ),'%'))) " +
            "OR (LOWER(u.user_phone) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, u.user_phone ),'%')))) ",
    nativeQuery = true)
    //Lấy tất cả khách hàng chưa xóa và có quyền đăng nhập là khách hàng và chủ trọ có tìm kiếm phân trang
    List<User> searchAllUserWithPagingation(Pageable page, @Param("search") SearchUserDTO search);

    @Query(value = "SELECT COUNT(u.user_id) FROM tbluser u WHERE (u.user_trash = 0 AND (u.user_roles = 1 OR u.user_roles = 2)) " +
            "AND ((LOWER(u.user_name) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, u.user_name ),'%'))) " +
            "OR (LOWER(u.user_phone) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, u.user_phone ),'%')))) ",
    nativeQuery = true)
    int countUser(@Param("search") SearchUserDTO search);

    User findUserByUserId(Long id);

    Boolean existsUserByUserName (String userName);

    Boolean existsUserByUserEmail (String userEmail);

    Boolean existsUserByUserPhone (String userPhone);

    User findUserByUserPhone(String phone);

    User findUserByUserEmail (String email);

    User findUserByUserNameAndUserPass(String username, String password);

    //Lay tong so khach hang
    @Query(value = "SELECT COUNT(user_id) FROM tbluser WHERE user_roles = 1",
    nativeQuery = true)
    int countGuest();

    //Lay tong so chu tro
    @Query(value = "SELECT COUNT(user_id) FROM tbluser WHERE user_roles = 2",
            nativeQuery = true)
    int countModel();

    //Lay tong so nguoi dung
    @Query(value = "SELECT COUNT(user_id) FROM tbluser WHERE user_roles = 2 OR user_roles = 1",
            nativeQuery = true)
    int countUserNotAdmin();
}
