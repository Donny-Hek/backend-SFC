package com.example.backendvkr.service;
import com.example.backendvkr.model.Authoriz;
import com.example.backendvkr.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Builder
@Data
@Service
@AllArgsConstructor
//@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final int id;
    private final String username; // email из Authoriz
    private final String firstName;
    private final String lastName;
//    private final String email;
    @JsonIgnore
    private final String password;// passwordHash из Authoriz
    private final Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole().name())
        );
        Authoriz authoriz = user.getAuthoriz();
        return new UserDetailsImpl(
                user.getId(),
                authoriz.getEmail(), // email из Authoriz
                authoriz.getPasswordHash(), // пароль из Authoriz
                user.getFirstName(),
                user.getLastName(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

