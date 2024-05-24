package com.datn.hoang.repository;

import com.datn.hoang.entity.Image;
import com.datn.hoang.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
