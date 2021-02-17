package com.greenbox.test.demo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails{

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_id_sequence_gen",
            sequenceName="user_id_sequence", initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence_gen")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<GreenBox> greenBoxList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ROLE_ASSIGNMENTS",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<Role> roles;

    @OneToMany(mappedBy = "userCreator")
    private List<GrowProgram> growPrograms;

    public User() {
    }

    public User(Long id, String username, String password, String email, List<GreenBox> greenBoxList, Set<Role> roles, List<GrowProgram> growPrograms) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.greenBoxList = greenBoxList;
        this.roles = roles;
        this.growPrograms = growPrograms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GreenBox> getGreenBoxList() {
        return greenBoxList;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGreenBoxList(List<GreenBox> greenBoxList) {
        this.greenBoxList = greenBoxList;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<GrowProgram> getGrowPrograms() {
        return growPrograms;
    }

    public void setGrowPrograms(List<GrowProgram> growPrograms) {
        this.growPrograms = growPrograms;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
