package com.example.backendvkr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "refresh_tokens")
@RequiredArgsConstructor
public class RefreshToken {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "token_hash", nullable = false)
//    private String tokenHash;
    private String token;

    @Column(name = "expires_at", nullable = false)
    private LocalDate expiresAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private LocalDate createdAt;

    public RefreshToken(String encode, User user) {
//        this.tokenHash=encode;
        this.user=user;
        this.token=encode;
        this.createdAt= LocalDate.now();
        this.expiresAt=LocalDate.now().plusDays(7);
    }
}