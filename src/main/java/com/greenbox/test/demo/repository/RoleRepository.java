package com.greenbox.test.demo.repository;


import com.greenbox.test.demo.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
