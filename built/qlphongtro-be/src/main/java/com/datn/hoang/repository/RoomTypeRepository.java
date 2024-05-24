package com.datn.hoang.repository;

import com.datn.hoang.entity.Article;
import com.datn.hoang.entity.RoomType;
import com.datn.hoang.services.dto.SearchDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    List<RoomType> findAll();
}
