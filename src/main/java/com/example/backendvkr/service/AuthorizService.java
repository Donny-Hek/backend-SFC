package com.example.backendvkr.service;

import com.example.backendvkr.dto.AuthResponseDto;
import com.example.backendvkr.dto.LoginDto;
import com.example.backendvkr.dto.RegisterDto;
import com.example.backendvkr.model.User;
import com.example.backendvkr.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
//    private final JwtGenerator jwtGenerator;

    public AuthorizService(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder
            //,JwtGenerator jwtGenerator
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
//        this.jwtGenerator = jwtGenerator;
    }

    public AuthResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getLogin(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtGenerator.generateToken(authentication);
//        User user = userRepository.findByLogin(loginDto.getLogin()).orElseThrow();
        return new AuthResponseDto(
//        token, user.getRole().name()
        );
    }

    public void register(RegisterDto registerDto) {
//        if (userRepository.existsByLogin(registerDto.getLogin())) {
            throw new RuntimeException("Username is already taken!");
//        }

//        User user = new User();
//        user.setLogin(registerDto.getLogin());
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//        user.setName(registerDto.getName());
//        user.setRole(Role.USER); // По умолчанию присваиваем роль USER

//        userRepository.save(user);
    }
}
