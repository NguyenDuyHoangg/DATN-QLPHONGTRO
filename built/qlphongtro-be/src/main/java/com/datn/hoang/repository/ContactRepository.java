package com.datn.hoang.repository;

import com.datn.hoang.entity.Contact;
import com.datn.hoang.entity.User;
import com.datn.hoang.entity.Wards;
import com.datn.hoang.services.dto.SearchContactDTO;
import com.datn.hoang.services.dto.SearchUserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    void deleteContactByContactId(Long id);

    @Query(value = "SELECT * FROM tblcontact c WHERE " +
            "c.contact_user_id = COALESCE(:#{#search.search}, c.contact_user_id) ",
            nativeQuery = true)
        //Lấy tất cả khách hàng chưa xóa và có quyền đăng nhập là khách hàng và chủ trọ có tìm kiếm phân trang
    List<Contact> searchAllContactPagingation(Pageable page, @Param("search") SearchContactDTO search);
    @Query(value = "SELECT COUNT(c.contact_id) FROM tblcontact c WHERE " +
            "c.contact_user_id = COALESCE(:#{#search.search}, c.contact_user_id) ",
            nativeQuery = true)
    int countContact(@Param("search") SearchContactDTO search);
}
