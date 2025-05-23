package com.example.backendvkr.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private final Role role=Role.USER;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subs_id", nullable = false)
    private Subscription subs;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
//    private RefreshToken refreshTokens;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    private Authoriz authoriz; //обязательное

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Examination> examinations;

    public User(String firstName, String lastName, String status, Subscription free, LocalDate now) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.status=status;
        this.subs=free;
        this.createdAt=now;
    }
}
