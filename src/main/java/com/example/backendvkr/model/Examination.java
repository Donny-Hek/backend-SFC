package com.example.backendvkr.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "examinations")
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "answers_file", nullable = false)
    private byte[] answersFile;

}
