package com.example.backendvkr.model;

import com.example.backendvkr.dto.TaskDto;
import com.example.backendvkr.repository.PhotoRepository;
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
//import org.springframework.web.multipart.MultipartFile;
//import org.hibernate.annotations.Type;
//import org.hibernate.annotations.TypeDef;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "examinations")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@RequiredArgsConstructor
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
//            (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "answers", nullable = false, columnDefinition = "jsonb")
//    @Type(type = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<TaskDto> answers;
//    private byte[] answersFile;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private LocalDate createdAt;

    @OneToMany(mappedBy = "examination",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Photo> photos = new LinkedHashSet<>();

    public Examination(List<TaskDto> answers, Subject subject, User user) {
        this.answers = answers;
        this.subject = subject;
        this.user = user;
        this.createdAt = LocalDate.now();
    }

    public void addPhotoToSet(Photo photo) {
        this.photos.add(photo);
    }
}
