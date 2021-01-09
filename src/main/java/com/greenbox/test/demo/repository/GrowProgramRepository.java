package com.greenbox.test.demo.repository;

import com.greenbox.test.demo.entity.GrowProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GrowProgramRepository extends JpaRepository<GrowProgram, Long> {
    List<GrowProgram> findAll();
}
