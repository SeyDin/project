package com.greenbox.test.demo.service;

import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.Role;
import com.greenbox.test.demo.entity.User;
import com.greenbox.test.demo.repository.RoleRepository;
import com.greenbox.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Service(value = "userDetailsService")
public class UserServiceImpl implements UserService {

    private static final Long ROLE_USER_ID = 1L;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Can't find user with username " + username);
        }
        return user; // так что он возвращает? UserDetails или User? или проксю мб?
    }

    @Override
    public User signupUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findById(ROLE_USER_ID).orElseThrow(IllegalStateException::new);
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = null;
        if (userDetails instanceof User) {
            user =  (User) userDetails;
        }
        return user;
    }

    @Override
    public boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }

    @Override
    public Set<GrowProgram> getFavoritesPrograms() {
        return null;
    }


}
