package com.example.backendvkr.service;

import com.example.backendvkr.model.Authoriz;
import com.example.backendvkr.model.User;
import com.example.backendvkr.repository.AuthorizRepository;
import com.example.backendvkr.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;
    private final AuthorizRepository authorizRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Authoriz authoriz = authorizRepository.findByEmail(username).
                orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));
        User user = userRepository.findUserById(authoriz.getId()).
                orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));
        ;
        return UserDetailsImpl.build(user);
    }
}
