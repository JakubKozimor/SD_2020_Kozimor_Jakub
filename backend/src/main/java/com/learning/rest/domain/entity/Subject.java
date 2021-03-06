package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learning.rest.domain.entity.enums.Day;
import com.learning.rest.domain.entity.enums.Week;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private Day day;
    @Enumerated(EnumType.STRING)
    @Column(name = "week")
    private Week week;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "subject_id")
    @JsonManagedReference
    private List<SubjectFile> files;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    @JsonManagedReference
    private List<Homework> homeworks;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "long_of_time")
    private Integer longOfTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private User teacher;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "subject_student",
            joinColumns = {@JoinColumn(name = "subject_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @JsonBackReference
    private Set<User> students;

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

    public void addStudent(User user) {
        if (students == null) {
            students = new HashSet<>();
        }
        students.add(user);
    }
}
