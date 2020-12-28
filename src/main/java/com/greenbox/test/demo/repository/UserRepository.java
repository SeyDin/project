package com.greenbox.test.demo.repository;

import com.greenbox.test.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
