package com.example.backendvkr.sequrity;

import com.example.backendvkr.model.Authoriz;
import com.example.backendvkr.model.User;
import com.example.backendvkr.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${jwt.access}")
    private String accessSecret;

//    @Value("${jwt.refresh}")
//    private String refreshSecret;

    @Value("${jwt.access.expiration}")
    private long accessExpiration;

//    @Value("${jwt.refresh.expiration}")
//    private long refreshExpiration;

    private Key key(String secret) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateAccessToken(UserDetailsImpl authentication) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", authentication.getId());
        claims.put("email", authentication.getEmail());
        claims.put("lastName", authentication.getLastName());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(authentication.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(key(accessSecret), SignatureAlgorithm.HS256)
                .compact();
    }

//    public String generateRefreshToken(UserDetailsImpl authentication) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", authentication.getId());
//        claims.put("email", authentication.getEmail());
//        claims.put("lastName", authentication.getLastName());
//        return Jwts.builder()
//                .setSubject(authentication.getEmail())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
//                .signWith(key(accessSecret), SignatureAlgorithm.HS256)
//                .compact();
//    }

    public boolean validateAccessToken(String token) {
        return validateToken(token, accessSecret);
    }

//    public boolean validateRefreshToken(String token) {
//        return validateToken(token, refreshSecret);
//    }

    private boolean validateToken(String token, String secret) {
        try {
            Jwts.parserBuilder().setSigningKey(key(secret)).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getEmailFromAccessToken(String token) {
        return getEmailFromToken(token,accessSecret);
    }

//    public String getEmailFromRefreshToken(String token) {
//        return getEmailFromToken(token,refreshSecret);
//    }

    public String getEmailFromToken(String token,String secret) {
        return Jwts.parserBuilder().setSigningKey(key(secret)).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
