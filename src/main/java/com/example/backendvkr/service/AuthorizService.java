package com.example.backendvkr.service;

import com.example.backendvkr.dto.AuthResponseDto;
import com.example.backendvkr.dto.LoginDto;
import com.example.backendvkr.dto.MessageResponse;
import com.example.backendvkr.dto.RegisterDto;
import com.example.backendvkr.model.Authoriz;
import com.example.backendvkr.model.User;
import com.example.backendvkr.repository.AuthorizRepository;
import com.example.backendvkr.repository.SubscriptionRepository;
import com.example.backendvkr.repository.UserRepository;
import com.example.backendvkr.security.JwtTokenUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthorizService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorizRepository authorizRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final RefreshTokenService refreshTokenService;

    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getLogin(),
                            loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String jwt = jwtTokenUtil.generateAccessToken(userDetails);
//        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

            return ResponseEntity.ok(new AuthResponseDto(
                    jwt,
                    "Bearer",
//                refreshToken.getToken(),
                    userDetails.getId(),
                    userDetails.getFirstName(),
                    userDetails.getLastName(),
                    userDetails.getEmail(),
                    userDetails.getStatus()
            ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Пользователь не найден"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Произошла ошибка при аутентификации"));
        }
    }

    @Transactional
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if (authorizRepository.existsByEmail(registerDto.getEmail())) {
            ResponseEntity.badRequest().body(new MessageResponse("Ошибка: Почта уже занята!"));
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
//        RefreshToken refreshToken = new RefreshToken(
//                passwordEncoder.encode(UUID.randomUUID().toString()));

//        user.setRefreshTokens(refreshToken);
//        userRepository.save(user);
        authoriz.setUser(user);
        user.setAuthoriz(authoriz);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Вы успешно зарегистрировались!"));
    }
}
