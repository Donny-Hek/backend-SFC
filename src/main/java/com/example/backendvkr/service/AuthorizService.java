package com.example.backendvkr.service;

import com.example.backendvkr.dto.AuthResponseDto;
import com.example.backendvkr.dto.LoginDto;
import com.example.backendvkr.dto.RegisterDto;
import com.example.backendvkr.model.Authoriz;
import com.example.backendvkr.model.RefreshToken;
import com.example.backendvkr.model.User;
import com.example.backendvkr.repository.AuthorizRepository;
import com.example.backendvkr.repository.SubscriptionRepository;
import com.example.backendvkr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthorizService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorizRepository authorizRepository;
    private final SubscriptionRepository subscriptionRepository;
//    private final JwtGenerator jwtGenerator;

//    public AuthResponseDto login(LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDto.getLogin(),
//                        loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtGenerator.generateToken(authentication);
//        User user = userRepository.findByLogin(loginDto.getLogin()).orElseThrow();
//        return new AuthResponseDto(
//        token, user.getRole().name()
//        );
//    }

    public void register(RegisterDto registerDto) {
        if (authorizRepository.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("Username is already taken!");
        }
        User user = new User(
                registerDto.getFirstName(),
                registerDto.getLastName(),
                registerDto.getStatus(),
                subscriptionRepository.getSubscriptionBySubsType("FREE"),
                LocalDate.now()
        );
        Authoriz authoriz = new Authoriz(
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                false);
//        RefreshToken refreshToken=new RefreshToken();
        user.setAuthoriz(authoriz);
        userRepository.save(user);
    }
}
