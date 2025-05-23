package com.example.backendvkr.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size", nullable = false)
    private Integer size;

    @Column(name = "file_photo", nullable = false)
    private byte[] filePhoto;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "uploaded_at")
    private LocalDate uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "examination_id", nullable = false)
    private Examination examination;

    public Photo(String originalFilename, int size, byte[] bytes, Examination examination) {
        this.name=originalFilename;
        this.size=size;
        this.filePhoto=bytes;
        this.examination=examination;
        this.uploadedAt=LocalDate.now();
    }
}