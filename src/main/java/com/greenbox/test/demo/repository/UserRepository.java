package com.greenbox.test.demo.repository;

import com.greenbox.test.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    User findById(Long id);

}
