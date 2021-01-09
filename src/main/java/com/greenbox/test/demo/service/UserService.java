package com.greenbox.test.demo.service;

import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    // Взято из: https://javarush.ru/groups/posts/2582-dobavljaem-bd-k-restful-servisu-na-spring-boot-chastjh-2 +-
/*
    void create(User user);
    List<User> readAll();
    User read(int id);
    boolean update(User user, int id);
    boolean delete(int id);
*/
    // Взято из mvc-example
    User signupUser(User user);
    User getCurrentUser();
    boolean hasRole(String role);
    Set<GrowProgram> getFavoritesPrograms();

}
