package com.example.backendvkr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "subs_type", nullable = false)
    private String subsType;

    @Column(name = "photos", nullable = false)
    private Integer photos;

    @Column(name = "checks", nullable = false)
    private Integer checks;

    public Subscription(String subsType, Integer photos, Integer checks) {
        this.subsType = subsType;
        this.photos = photos;
        this.checks = checks;
    }
}