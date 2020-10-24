package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lessons_files")
@Getter
@Setter
@NoArgsConstructor
public class LessonFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long lessonFileId;
    @Column(name = "name")
    private String fileName;
    @Lob
    @Column(name = "file")
    private byte[] fileContent;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "lesson_id")
    @JsonBackReference
    private Lesson lesson;
}
