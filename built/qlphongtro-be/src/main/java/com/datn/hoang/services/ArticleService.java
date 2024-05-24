package com.datn.hoang.services;

import com.datn.hoang.services.dto.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    //Lấy các bài đăng phân trang
    List<Map<String, Object>> getAllArticleNewest();

    //Lấy baì đăng theo id
    List<Map<String, Object>> getArticleById (Long id);

    //Lấy bài đăng đã duyệt hay chờ duyệt
    List<Map<String, Object>> getAllArticleWithApprovedStatus(Pageable page, Long approvedStatus);

    //Lấy bài đăng chưa được kiểm duyệt
    List<Map<String, Object>> getAllArticleByNotApproved(Pageable page, Long articleStatus);

    //Lấy bài đăng đã được kiểm duyệt
    List<Map<String, Object>> getAllArticleByApproved(Pageable page, Long articleStatus, Long articleApproved);

    //Lấy bài đăng đã bị khóa
    List<Map<String, Object>> getAllArticleBlocked(Pageable page);

    //Duyệt bài đăng
    ServiceResult<ArticleDTO> ApproveArticle(Long id,Long articleApproved, Long articleStatus, Long articleNotApproved);

    //Hủy bài đăng
    ServiceResult<ArticleDTO> NotApproveArticle(Long id,Long articleApproved, Long articleStatus);

    //Khóa bài đăng
    ServiceResult<ArticleDTO> BlockArticle(Long id,Long articleBlocked);

    //Tìm kiếm và hiển thị bài đăng có phân trang
    ServiceResult<Map<String, Object>> searchArticleWithPagination(SearchDTO searchOption);

    //Tìm kiếm và hiển thị bài đăng có phân trang đã được duyệt
    ServiceResult<Map<String, Object>> searchArticleWithPagination2(SearchDTO searchOption);

    //Tìm kiếm và hiển thị bài đăng theo ngày tạo mới nhất
    ServiceResult<Map<String, Object>> searchArticleWithNewsest(SearchDTO searchOption);

    ServiceResult<RoomDTO> create(RoomDTO roomDTO);

    ServiceResult<RoomDTO> update(ArticleUpdateDTO articleUpdateDTO) throws EntityNotFoundException;

    ServiceResult<Map<String, Object>> getAllArticleByUserId(PagingUserIdDTO pagingUserIdDTO);

    ServiceResult<ArticleDTO> delete(Long id);

    ServiceResult<Map<String, Object>> searchArticleWithPagination3(Long id, SearchDTO searchOption);

    //Tìm kiếm và hiển thị bài đăng theo ngày tạo mới nhất
    ServiceResult<Map<String, Object>> searchArticleWithNewsest2(Long id, SearchDTO searchOption);
}
