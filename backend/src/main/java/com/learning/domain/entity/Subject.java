package com.learning.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;
    @Column(name = "subject_name")
    private String name;
    @Column(name = "subject_date")
    private Date subjectDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    @JsonManagedReference
    private List<SubjectFile> files;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    @JsonManagedReference
    private List<Homework> homeworks;

    public void addFile(SubjectFile lessonFile) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.add(lessonFile);
    }

    public void addHomework(Homework homework) {
        if (homeworks == null) {
            homeworks = new ArrayList<>();
        }
        homeworks.add(homework);
    }
}
