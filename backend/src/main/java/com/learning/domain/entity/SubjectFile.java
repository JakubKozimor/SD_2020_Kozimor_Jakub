package com.learning.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subject_files")
@Getter
@Setter
@NoArgsConstructor
public class SubjectFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long lessonFileId;
    @Column(name = "name")
    private String fileName;
    @Column(name = "file")
    private String fileContent;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    private Subject subject;
}
