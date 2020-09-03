package com.learning.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "homeworks")
@Getter
@Setter
@NoArgsConstructor
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homework_id")
    private Long subjectId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "deadline")
    private Date deadline;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "homework_id")
    private List<HomeworkFile> files;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    private Subject subject;

    public void addFile(HomeworkFile homeworkFile) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.add(homeworkFile);
    }
}
