package com.example.backendvkr.service;

import com.example.backendvkr.repository.RefreshTokenRepository;
import com.example.backendvkr.repository.UserRepository;
import com.example.backendvkr.sequrity.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

//    public ResponseEntity<?> refreshtoken(String request) {
//        String requestRefreshToken = request;
//
//        return findByToken(requestRefreshToken)
//                .map(token -> {
//                    // Проверяем expiration с помощью метода verifyExpiration
//                    RefreshToken verifiedToken = verifyExpiration(token);
//
//                    // Получаем данные пользователя
//                    User user = verifiedToken.getUser();
//                    UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService
//                            .loadUserByUsername(user.getUsername());
//
//                    // Удаляем использованный refresh token
//                    refreshTokenRepository.delete(verifiedToken);
//
//                    // Генерируем новый access token
//                    String newToken = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
//
//                    // Создаем новый refresh token
//                    RefreshToken newRefreshToken = createRefreshToken(user.getId());
//
//                    return ResponseEntity.ok(new JwtResponse(
//                            newToken,
//                            newRefreshToken.getToken(),
//                            userDetails.getId(),
//                            userDetails.getUsername(),
//                            userDetails.getEmail(),
//                            userDetails.getRoles()));
//                })
//                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!"));
//    }

//    public Optional<RefreshToken> findByToken(String token) {
//        return refreshTokenRepository.findByToken(token);
//    }
//
//    public RefreshToken createRefreshToken(Integer userId) {
//        RefreshToken refreshToken = new RefreshToken(
//                UUID.randomUUID().toString(),
//                userRepository.findById(userId).get()
//        );
//
//        refreshToken = refreshTokenRepository.save(refreshToken);
//        return refreshToken;
//    }
//
//    public RefreshToken verifyExpiration(RefreshToken token) {
//        if (token.getExpiresAt().compareTo(LocalDate.now()) < 0) {
//            refreshTokenRepository.delete(token);
//            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
//        }
//
//        return token;
//    }
//
//    @Transactional
//    public int deleteByUserId(Integer userId) {
//        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
//    }
}
