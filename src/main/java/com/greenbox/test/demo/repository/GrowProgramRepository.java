package com.greenbox.test.demo.repository;

import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface GrowProgramRepository extends JpaRepository<GrowProgram, Long> {
    List<GrowProgram> findAll();
    List<GrowProgram> findAllByUserCreator(User user);

}
