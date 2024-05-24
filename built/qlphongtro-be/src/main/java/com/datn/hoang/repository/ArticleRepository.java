package com.datn.hoang.repository;

import com.datn.hoang.entity.Article;
import com.datn.hoang.services.dto.SearchDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_summary, " +
                    "u.user_id, u.user_name, u.user_image, u.user_fullname,  " +
                    "rt.room_type_id, rt.room_type_name,   " +
                    "l.location_id, l.location_address,   " +
                    "p.code AS provinceCode, p.full_name AS provinceName,   " +
                    "d.code AS districtCode, d.full_name AS districtName,   " +
                    "w.code AS wardCode, w.full_name AS wardName,   " +
                    "r.room_id, r.room_acreage, r.room_price, r.room_status,   " +
                    "r.room_internet, r.room_parking, r.room_airconditioner,   " +
                    "r.room_heater, r.room_television, r.room_toilet,   " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName,   " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName,   " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName,   " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName,   " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName,   " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName,   " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName,   " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName,   " +
                    "i.image_id, i.image_name, i.image_url   " +
                    "FROM tblarticle a   " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id   " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id   " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id   " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id   " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id   " +
                    "JOIN wards w ON w.code = l.location_ward_code   " +
                    "JOIN districts d ON d.code = w.district_code   " +
                    "JOIN provinces p ON p.code = d.province_code   " +
                    "WHERE a.article_blocked=0 AND a.article_status=1 AND a.article_approved=1 " +
                    "GROUP BY a.article_id " +
                    "ORDER BY a.article_created_date DESC LIMIT 5",
            nativeQuery = true
    )
    List<Map<String, Object>> getAllArticleNewest();

    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_expiration_date, " +
                    "u.user_id, u.user_name, u.user_phone, u.user_zalo, u.user_image, u.user_fullname, " +
                    "rt.room_type_id, rt.room_type_name, " +
                    "l.location_id, l.location_address, " +
                    "p.code AS provinceCode, p.full_name AS provinceName, " +
                    "d.code AS districtCode, d.full_name AS districtName, " +
                    "w.code AS wardCode, w.full_name AS wardName, " +
                    "r.*, " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName, " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName, " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName, " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName, " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName, " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName, " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName, " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName, " +
                    "i.image_id, i.image_name, i.image_url " +
                    "FROM tblarticle a " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_id = ?1 " +
                    "GROUP BY a.article_id",
            nativeQuery = true
    )
    List<Map<String, Object>> getArticleById(Long id);

    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_expiration_date, " +
                    "u.user_id, u.user_name, " +
                    "rt.room_type_id, rt.room_type_name, " +
                    "l.location_id, l.location_address, " +
                    "p.code AS provinceCode, p.full_name AS provinceName, " +
                    "d.code AS districtCode, d.full_name AS districtName, " +
                    "w.code AS wardCode, w.full_name AS wardName, " +
                    "r.room_id, r.room_acreage, r.room_price, r.room_status, " +
                    "r.room_internet, r.room_parking, r.room_airconditioner, " +
                    "r.room_heater, r.room_television, r.room_toilet, " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName, " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName, " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName, " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName, " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName, " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName, " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName, " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName, " +
                    "i.image_id, i.image_name, i.image_url " +
                    "FROM tblarticle a " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_status=?1 AND a.article_blocked=0 " +
                    "GROUP BY a.article_id",
            nativeQuery = true
    )
        // Lấy bài đăng chưa được kiểm duyệt
    List<Map<String, Object>> getAllArticleByNotApproved(Pageable page, Long articleStatus);

    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_expiration_date, " +
                    "u.user_id, u.user_name, " +
                    "rt.room_type_id, rt.room_type_name, " +
                    "l.location_id, l.location_address, " +
                    "p.code AS provinceCode, p.full_name AS provinceName, " +
                    "d.code AS districtCode, d.full_name AS districtName, " +
                    "w.code AS wardCode, w.full_name AS wardName, " +
                    "r.room_id, r.room_acreage, r.room_price, r.room_status, " +
                    "r.room_internet, r.room_parking, r.room_airconditioner, " +
                    "r.room_heater, r.room_television, r.room_toilet, " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName, " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName, " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName, " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName, " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName, " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName, " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName, " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName, " +
                    "i.image_id, i.image_name, i.image_url " +
                    "FROM tblarticle a " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_status=?1 AND a.article_blocked=0 AND a.article_approved=?2 " +
                    "GROUP BY a.article_id",
            nativeQuery = true
    )
    // Lấy bài đăng đã được kiểm duyệt
    List<Map<String, Object>> getAllArticleByApproved(Pageable page, Long articleStatus, Long articleApproved);

    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_expiration_date, " +
                    "u.user_id, u.user_name, " +
                    "rt.room_type_id, rt.room_type_name, " +
                    "l.location_id, l.location_address, " +
                    "p.code AS provinceCode, p.full_name AS provinceName, " +
                    "d.code AS districtCode, d.full_name AS districtName, " +
                    "w.code AS wardCode, w.full_name AS wardName, " +
                    "r.room_id, r.room_acreage, r.room_price, r.room_status, " +
                    "r.room_internet, r.room_parking, r.room_airconditioner, " +
                    "r.room_heater, r.room_television, r.room_toilet, " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName, " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName, " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName, " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName, " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName, " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName, " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName, " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName, " +
                    "i.image_id, i.image_name, i.image_url " +
                    "FROM tblarticle a " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_status=?1 AND a.article_blocked=0 " +
                    "GROUP BY a.article_id",
            nativeQuery = true
    )
    List<Map<String, Object>> getAllArticleWithApprovedStatus(Pageable page, Long approvedStatus);

    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_expiration_date, " +
                    "u.user_id, u.user_name, " +
                    "rt.room_type_id, rt.room_type_name, " +
                    "l.location_id, l.location_address, " +
                    "p.code AS provinceCode, p.full_name AS provinceName, " +
                    "d.code AS districtCode, d.full_name AS districtName, " +
                    "w.code AS wardCode, w.full_name AS wardName, " +
                    "r.room_id, r.room_acreage, r.room_price, r.room_status, " +
                    "r.room_internet, r.room_parking, r.room_airconditioner, " +
                    "r.room_heater, r.room_television, r.room_toilet, " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName, " +
                    "CASE WHEN a.article_blocked = 0 THEN '' ELSE 'Đã khóa' END AS articleBlockedName, " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName, " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName, " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName, " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName, " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName, " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName, " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName, " +
                    "i.image_id, i.image_name, i.image_url " +
                    "FROM tblarticle a " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_blocked=1 " +
                    "GROUP BY a.article_id",
            nativeQuery = true
    )
    List<Map<String, Object>> getAllArticleBlocked(Pageable page);

    @Query(
            value = "SELECT * FROM tblarticle a " +
                    "WHERE a.article_id=?1 ",
            nativeQuery = true
    )
    Article findArticleById(Long id);

    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_summary, a.article_expiration_date, " +
                    "u.user_id, u.user_name, u.user_image, u.user_fullname,  " +
                    "rt.room_type_id, rt.room_type_name,   " +
                    "l.location_id, l.location_address,   " +
                    "p.code AS provinceCode, p.full_name AS provinceName,   " +
                    "d.code AS districtCode, d.full_name AS districtName,   " +
                    "w.code AS wardCode, w.full_name AS wardName,   " +
                    "r.room_id, r.room_acreage, r.room_price, r.room_status,   " +
                    "r.room_internet, r.room_parking, r.room_airconditioner,   " +
                    "r.room_heater, r.room_television, r.room_toilet,   " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName,   " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName,   " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName,   " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName,   " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName,   " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName,   " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName,   " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName,   " +
                    "i.image_id, i.image_name, i.image_url   " +
                    "FROM tblarticle a   " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id   " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id   " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id   " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id   " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id   " +
                    "JOIN wards w ON w.code = l.location_ward_code   " +
                    "JOIN districts d ON d.code = w.district_code   " +
                    "JOIN provinces p ON p.code = d.province_code   " +
                    "WHERE a.article_blocked=0 AND a.article_not_approved=0  " +
                    "AND ((LOWER(a.article_title) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, a.article_title ),'%')))   " +
                    "OR (LOWER(l.location_address) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, l.location_address ),'%')))  " +
                    "OR (LOWER(d.full_name) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, d.full_name ),'%')))  " +
                    "OR (LOWER(w.full_name) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, w.full_name ),'%'))))  " +
                    "AND LOWER(d.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.district}, d.code ),'%'))   " +
                    "AND LOWER(d.full_name) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.district}, d.full_name ),'%'))   " +
                    "AND LOWER(w.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.wards}, w.code ),'%'))   " +
                    "AND LOWER(w.full_name) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.wards}, w.full_name ),'%'))   " +
                    "AND (rt.room_type_id = COALESCE(:#{#search.roomTypeId}, rt.room_type_id)) " +
                    "AND (r.room_price BETWEEN COALESCE(:#{#search.minPrice}, r.room_price) AND COALESCE(:#{#search.maxPrice}, r.room_price))   " +
                    "AND (r.room_acreage BETWEEN COALESCE(:#{#search.minArea}, r.room_acreage) AND COALESCE(:#{#search.maxArea}, r.room_acreage)) " +
                    "GROUP BY a.article_id",
            nativeQuery = true
    )
    List<Map<String, Object>> searchArticleWithPagination(Pageable page, @Param("search") SearchDTO search);

    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_summary, " +
                    "u.user_id, u.user_name, u.user_image, u.user_fullname,  " +
                    "rt.room_type_id, rt.room_type_name,   " +
                    "l.location_id, l.location_address,   " +
                    "p.code AS provinceCode, p.full_name AS provinceName,   " +
                    "d.code AS districtCode, d.full_name AS districtName,   " +
                    "w.code AS wardCode, w.full_name AS wardName,   " +
                    "r.room_id, r.room_acreage, r.room_price, r.room_status,   " +
                    "r.room_internet, r.room_parking, r.room_airconditioner,   " +
                    "r.room_heater, r.room_television, r.room_toilet,   " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName,   " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName,   " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName,   " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName,   " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName,   " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName,   " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName,   " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName,   " +
                    "i.image_id, i.image_name, i.image_url   " +
                    "FROM tblarticle a   " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id   " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id   " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id   " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id   " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id   " +
                    "JOIN wards w ON w.code = l.location_ward_code   " +
                    "JOIN districts d ON d.code = w.district_code   " +
                    "JOIN provinces p ON p.code = d.province_code   " +
                    "WHERE a.article_blocked=0 AND a.article_status=1 AND a.article_approved=1 " +
                    "AND LOWER(d.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.district}, d.code ),'%'))   " +
                    "AND LOWER(w.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.wards}, w.code ),'%'))   " +
                    "AND (rt.room_type_id = COALESCE(:#{#search.roomTypeId}, rt.room_type_id)) " +
                    "AND (r.room_price BETWEEN COALESCE(:#{#search.minPrice}, r.room_price) AND COALESCE(:#{#search.maxPrice}, r.room_price))   " +
                    "AND (r.room_acreage BETWEEN COALESCE(:#{#search.minArea}, r.room_acreage) AND COALESCE(:#{#search.maxArea}, r.room_acreage)) " +
                    "GROUP BY a.article_id",
            nativeQuery = true
    )
    List<Map<String, Object>> searchArticleWithPagination2(Pageable page, @Param("search") SearchDTO search);

    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_summary, " +
                    "u.user_id, u.user_name, u.user_image, u.user_fullname,  " +
                    "rt.room_type_id, rt.room_type_name,   " +
                    "l.location_id, l.location_address,   " +
                    "p.code AS provinceCode, p.full_name AS provinceName,   " +
                    "d.code AS districtCode, d.full_name AS districtName,   " +
                    "w.code AS wardCode, w.full_name AS wardName,   " +
                    "r.room_id, r.room_acreage, r.room_price, r.room_status,   " +
                    "r.room_internet, r.room_parking, r.room_airconditioner,   " +
                    "r.room_heater, r.room_television, r.room_toilet,   " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName,   " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName,   " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName,   " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName,   " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName,   " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName,   " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName,   " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName,   " +
                    "i.image_id, i.image_name, i.image_url   " +
                    "FROM tblarticle a   " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id   " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id   " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id   " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id   " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id   " +
                    "JOIN wards w ON w.code = l.location_ward_code   " +
                    "JOIN districts d ON d.code = w.district_code   " +
                    "JOIN provinces p ON p.code = d.province_code   " +
                    "WHERE a.article_blocked=0 AND a.article_status=1 AND a.article_approved=1 " +
                    "AND LOWER(d.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.district}, d.code ),'%'))   " +
                    "AND LOWER(w.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.wards}, w.code ),'%'))   " +
                    "AND (rt.room_type_id = COALESCE(:#{#search.roomTypeId}, rt.room_type_id)) " +
                    "AND (r.room_price BETWEEN COALESCE(:#{#search.minPrice}, r.room_price) AND COALESCE(:#{#search.maxPrice}, r.room_price))   " +
                    "AND (r.room_acreage BETWEEN COALESCE(:#{#search.minArea}, r.room_acreage) AND COALESCE(:#{#search.maxArea}, r.room_acreage)) " +
                    "GROUP BY a.article_id " +
                    "ORDER BY a.article_created_date DESC ",
            nativeQuery = true
    )
    List<Map<String, Object>> searchArticleNewest(Pageable page, @Param("search") SearchDTO search);

    @Query(
            value = "SELECT COUNT(DISTINCT a.article_id) " +
                    "FROM tblarticle a  " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id  " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id  " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id  " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id  " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id  " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_blocked=0 AND a.article_not_approved=0   " +
                    "AND ((LOWER(a.article_title) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, a.article_title ),'%')))   " +
                    "OR (LOWER(l.location_address) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, l.location_address ),'%')))  " +
                    "OR (LOWER(d.full_name) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, d.full_name ),'%')))  " +
                    "OR (LOWER(w.full_name) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.searchString}, w.full_name ),'%'))))  " +
                    "AND LOWER(d.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.district}, d.code ),'%'))   " +
                    "AND LOWER(d.full_name) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.district}, d.full_name ),'%'))   " +
                    "AND LOWER(w.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.wards}, w.code ),'%'))   " +
                    "AND LOWER(w.full_name) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.wards}, w.full_name ),'%'))   " +
                    "AND (rt.room_type_id = COALESCE(:#{#search.roomTypeId}, rt.room_type_id)) " +
                    "AND (r.room_price BETWEEN COALESCE(:#{#search.minPrice}, r.room_price) AND COALESCE(:#{#search.maxPrice}, r.room_price))   " +
                    "AND (r.room_acreage BETWEEN COALESCE(:#{#search.minArea}, r.room_acreage) AND COALESCE(:#{#search.maxArea}, r.room_acreage)) ",
            nativeQuery = true
    )
    int countAllArticleWithPagination(@Param("search") SearchDTO search);

    @Query(
            value = "SELECT COUNT(DISTINCT a.article_id) " +
                    "FROM tblarticle a  " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id  " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id  " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id  " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id  " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id  " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_blocked=0 AND a.article_status=1 AND a.article_approved=1 " +
                    "AND LOWER(d.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.district}, d.code ),'%'))   " +
                    "AND LOWER(w.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.wards}, w.code ),'%'))   " +
                    "AND (rt.room_type_id = COALESCE(:#{#search.roomTypeId}, rt.room_type_id)) " +
                    "AND (r.room_price BETWEEN COALESCE(:#{#search.minPrice}, r.room_price) AND COALESCE(:#{#search.maxPrice}, r.room_price))   " +
                    "AND (r.room_acreage BETWEEN COALESCE(:#{#search.minArea}, r.room_acreage) AND COALESCE(:#{#search.maxArea}, r.room_acreage)) ",
            nativeQuery = true
    )
    int countAllArticleWithPagination2(@Param("search") SearchDTO search);

    Boolean existsArticlesByArticleUserId (Long id);

    //Tong so bai dang chua duoc duyet
    @Query(
            value = "SELECT COUNT(DISTINCT a.article_id) " +
                    "FROM tblarticle a " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_status=0 AND a.article_blocked=0 ",
            nativeQuery = true
    )
    int countArticleByNotApproved();

    //Tong so bai dang da duoc duyet
    @Query(
            value = "SELECT COUNT(DISTINCT a.article_id) " +
                    "FROM tblarticle a " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_status=1 AND a.article_blocked=0 AND a.article_approved=1 ",
            nativeQuery = true
    )
    int countArticleApproved();

    //Tong so bai dang da khoa
    @Query(
            value = "SELECT COUNT(DISTINCT a.article_id) " +
                    "FROM tblarticle a " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_blocked=1 ",
            nativeQuery = true
    )
    int countArticleBlocked();

    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_expiration_date, " +
                    "u.user_id, u.user_name, " +
                    "rt.room_type_id, rt.room_type_name, " +
                    "l.location_id, l.location_address, " +
                    "p.code AS provinceCode, p.full_name AS provinceName, " +
                    "d.code AS districtCode, d.full_name AS districtName, " +
                    "w.code AS wardCode, w.full_name AS wardName, " +
                    "r.room_id, r.room_acreage, r.room_price, r.room_status, " +
                    "r.room_internet, r.room_parking, r.room_airconditioner, " +
                    "r.room_heater, r.room_television, r.room_toilet, " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName, " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName, " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName, " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName, " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName, " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName, " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName, " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName, " +
                    "i.image_id, i.image_name, i.image_url " +
                    "FROM tblarticle a " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_status=1 AND a.article_blocked=0 AND a.article_approved=1 AND u.user_id=?1 AND a.article_not_approved=0 " +
                    "GROUP BY a.article_id",
            nativeQuery = true
    )
        // Lấy bài đăng theo userid đã được kiểm duyệt
    List<Map<String, Object>> getAllArticleByUserId(Pageable page, Long userId);

    //Đếm tổng số bài đăng theo userid
    @Query(
            value = "SELECT COUNT(DISTINCT a.article_id) " +
                    "FROM tblarticle a " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_status=1 AND a.article_blocked=0 AND a.article_approved=1 AND u.user_id=?1 AND a.article_not_approved=0 ",
            nativeQuery = true
    )
    int countArticleUserId(Long userId);

    @Query(value = "SELECT a.*, r.*, rt.*, l.*, u.*, i.*, w.code AS wardCode, d.code AS districtCode, p.code AS provinceCode "+
            "FROM tblarticle a   "+
            "JOIN tblroom r ON a.article_room_id=r.room_id   "+
            "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id   "+
            "JOIN tbllocation l ON l.location_id = r.room_location_id   "+
            "JOIN tbluser u ON u.user_id = a.article_user_id   "+
            "JOIN tblimage i ON i.image_article_id = a.article_id   "+
            "JOIN wards w ON w.code = l.location_ward_code   "+
            "JOIN districts d ON d.code = w.district_code   "+
            "JOIN provinces p ON p.code = d.province_code   "+
            "WHERE a.article_status=1 AND a.article_blocked=0 AND a.article_approved=1 AND u.user_id=?1 AND a.article_id = ?2 "+
            "GROUP BY a.article_id ", nativeQuery = true)
    List<Map<String,Object>> getArticleByUserId(Long userId, Long articleId);

    //Xem theo danh muc
    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_summary, " +
                    "u.user_id, u.user_name, u.user_image, u.user_fullname,  " +
                    "rt.room_type_id, rt.room_type_name,   " +
                    "l.location_id, l.location_address,   " +
                    "p.code AS provinceCode, p.full_name AS provinceName,   " +
                    "d.code AS districtCode, d.full_name AS districtName,   " +
                    "w.code AS wardCode, w.full_name AS wardName,   " +
                    "r.room_id, r.room_acreage, r.room_price, r.room_status,   " +
                    "r.room_internet, r.room_parking, r.room_airconditioner,   " +
                    "r.room_heater, r.room_television, r.room_toilet,   " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName,   " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName,   " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName,   " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName,   " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName,   " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName,   " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName,   " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName,   " +
                    "i.image_id, i.image_name, i.image_url   " +
                    "FROM tblarticle a   " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id   " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id   " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id   " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id   " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id   " +
                    "JOIN wards w ON w.code = l.location_ward_code   " +
                    "JOIN districts d ON d.code = w.district_code   " +
                    "JOIN provinces p ON p.code = d.province_code   " +
                    "WHERE a.article_blocked=0 AND a.article_status=1 AND a.article_approved=1 AND rt.room_type_id=?1 " +
                    "AND LOWER(d.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.district}, d.code ),'%'))   " +
                    "AND LOWER(w.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.wards}, w.code ),'%'))   " +
                    "AND (rt.room_type_id = COALESCE(:#{#search.roomTypeId}, rt.room_type_id)) " +
                    "AND (r.room_price BETWEEN COALESCE(:#{#search.minPrice}, r.room_price) AND COALESCE(:#{#search.maxPrice}, r.room_price))   " +
                    "AND (r.room_acreage BETWEEN COALESCE(:#{#search.minArea}, r.room_acreage) AND COALESCE(:#{#search.maxArea}, r.room_acreage)) " +
                    "GROUP BY a.article_id",
            nativeQuery = true
    )
    List<Map<String, Object>> searchArticleWithPagination3(Pageable page,Long id, @Param("search") SearchDTO search );

    @Query(
            value = "SELECT a.article_id, a.article_title, a.article_created_date, a.article_approved, a.article_status, a.article_content, a.article_summary, " +
                    "u.user_id, u.user_name, u.user_image, u.user_fullname,  " +
                    "rt.room_type_id, rt.room_type_name,   " +
                    "l.location_id, l.location_address,   " +
                    "p.code AS provinceCode, p.full_name AS provinceName,   " +
                    "d.code AS districtCode, d.full_name AS districtName,   " +
                    "w.code AS wardCode, w.full_name AS wardName,   " +
                    "r.room_id, r.room_acreage, r.room_price, r.room_status,   " +
                    "r.room_internet, r.room_parking, r.room_airconditioner,   " +
                    "r.room_heater, r.room_television, r.room_toilet,   " +
                    "CASE WHEN a.article_status = 0 THEN 'Chờ duyệt' ELSE 'Đã duyệt' END AS articleStatusName,   " +
                    "CASE WHEN r.room_status = 0 THEN 'Hết phòng' ELSE 'Còn phòng' END AS roomStatusName,   " +
                    "CASE WHEN r.room_internet = 0 THEN '' ELSE 'Internet' END AS roomInternetName,   " +
                    "CASE WHEN r.room_parking = 0 THEN '' ELSE 'Chỗ để xe' END AS roomParkingName,   " +
                    "CASE WHEN r.room_airconditioner = 0 THEN '' ELSE 'Điều hòa' END AS roomAirconditionerName,   " +
                    "CASE WHEN r.room_heater = 0 THEN '' ELSE 'Nóng lạnh' END AS roomHeaterName,   " +
                    "CASE WHEN r.room_television = 0 THEN '' ELSE 'TV' END AS roomTVName,   " +
                    "CASE WHEN r.room_toilet = 0 THEN 'Không khép kín' ELSE 'Khép kín' END AS roomToiletName,   " +
                    "i.image_id, i.image_name, i.image_url   " +
                    "FROM tblarticle a   " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id   " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id   " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id   " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id   " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id   " +
                    "JOIN wards w ON w.code = l.location_ward_code   " +
                    "JOIN districts d ON d.code = w.district_code   " +
                    "JOIN provinces p ON p.code = d.province_code   " +
                    "WHERE a.article_blocked=0 AND a.article_status=1 AND a.article_approved=1 AND rt.room_type_id=?1 " +
                    "AND LOWER(d.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.district}, d.code ),'%'))   " +
                    "AND LOWER(w.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.wards}, w.code ),'%'))   " +
                    "AND (rt.room_type_id = COALESCE(:#{#search.roomTypeId}, rt.room_type_id)) " +
                    "AND (r.room_price BETWEEN COALESCE(:#{#search.minPrice}, r.room_price) AND COALESCE(:#{#search.maxPrice}, r.room_price))   " +
                    "AND (r.room_acreage BETWEEN COALESCE(:#{#search.minArea}, r.room_acreage) AND COALESCE(:#{#search.maxArea}, r.room_acreage)) " +
                    "GROUP BY a.article_id " +
                    "ORDER BY a.article_created_date DESC ",
            nativeQuery = true
    )
    List<Map<String, Object>> searchArticleNewest2(Pageable page, Long id, @Param("search") SearchDTO search);

    @Query(
            value = "SELECT COUNT(DISTINCT a.article_id) " +
                    "FROM tblarticle a  " +
                    "JOIN tblroom r ON a.article_room_id=r.room_id  " +
                    "JOIN tblroom_type rt ON rt.room_type_id = r.room_roomType_id  " +
                    "JOIN tbllocation l ON l.location_id = r.room_location_id  " +
                    "JOIN tbluser u ON u.user_id = a.article_user_id  " +
                    "JOIN tblimage i ON i.image_article_id = a.article_id  " +
                    "JOIN wards w ON w.code = l.location_ward_code " +
                    "JOIN districts d ON d.code = w.district_code " +
                    "JOIN provinces p ON p.code = d.province_code " +
                    "WHERE a.article_blocked=0 AND a.article_status=1 AND a.article_approved=1 AND rt.room_type_id=?1 " +
                    "AND LOWER(d.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.district}, d.code ),'%'))   " +
                    "AND LOWER(w.code) LIKE LOWER(CONCAT('%',COALESCE(:#{#search.wards}, w.code ),'%'))   " +
                    "AND (rt.room_type_id = COALESCE(:#{#search.roomTypeId}, rt.room_type_id)) " +
                    "AND (r.room_price BETWEEN COALESCE(:#{#search.minPrice}, r.room_price) AND COALESCE(:#{#search.maxPrice}, r.room_price))   " +
                    "AND (r.room_acreage BETWEEN COALESCE(:#{#search.minArea}, r.room_acreage) AND COALESCE(:#{#search.maxArea}, r.room_acreage)) ",
            nativeQuery = true
    )
    int countAllArticleWithPagination3(Long id,@Param("search") SearchDTO search);

}
