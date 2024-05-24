package com.datn.hoang.repository;

import com.datn.hoang.entity.Districts;
import com.datn.hoang.entity.Wards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Wards, String> {
    List<Wards> findWardsByDistrictCode(String districtCode);

    Wards findWardsByCode(String code);
}
