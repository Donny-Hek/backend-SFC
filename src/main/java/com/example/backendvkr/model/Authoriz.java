package com.example.backendvkr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "authoriz")
public class Authoriz {
    @Id
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @Column(name = "password_hash", nullable = false)
    @JsonIgnore
    private String passwordHash;

    @ColumnDefault("false")
    @Column(name = "is_verified")
    private Boolean isVerified;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "email", nullable = false, referencedColumnName = "email")
    private User email;

}