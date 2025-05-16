package com.example.backendvkr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "subj_type", length = 10)
    private String subjType;

    @Column(name = "questions")
    private Integer questions;

    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY)
    private Set<Examination> examinations = new LinkedHashSet<>();


}