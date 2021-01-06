package com.greenbox.test.demo.repository;

import com.greenbox.test.demo.entity.GreenBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GreenBoxRepository extends JpaRepository<GreenBox, Long> {
    List<GreenBox> findAllByUserId(Long userId); // Integer или Long?
}
