package com.greenbox.test.demo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails{

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_id_sequence_gen",
            sequenceName="user_id_sequence", initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence_gen")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    //Так правильно делать?
    // https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/one-to-many-foreign-key-mapping.html
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<GreenBox> greenBoxList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ROLE_ASSIGNMENTS",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<Role> roles;


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
