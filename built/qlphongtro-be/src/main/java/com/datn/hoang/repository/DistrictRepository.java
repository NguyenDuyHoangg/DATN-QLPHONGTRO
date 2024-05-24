package com.datn.hoang.repository;

import com.datn.hoang.entity.Districts;
import com.datn.hoang.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<Districts, String> {
    List<Districts> findAll();
}
