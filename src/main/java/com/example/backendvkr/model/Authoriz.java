package com.example.backendvkr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "authoriz")
@RequiredArgsConstructor
public class Authoriz {
    @Id
    private Integer id;//равен user_id

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password_hash", nullable = false)
    @JsonIgnore
    private String passwordHash;

    @ColumnDefault("false")
    @Column(name = "is_verified")
    private Boolean isVerified;

    public Authoriz(String email, String encode, boolean b) {
        this.email=email;
        this.passwordHash=encode;
        this.isVerified=b;
    }

    //@OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    //@JoinColumn(name = "email", nullable = false, referencedColumnName = "email")


}