package com.datn.hoang.repository;

import com.datn.hoang.entity.Location;
import com.datn.hoang.entity.Wards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
