package com.greenbox.test.demo.repository;

import com.greenbox.test.demo.entity.GreenBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreenBoxRepository extends JpaRepository<GreenBox, Integer> {
}
