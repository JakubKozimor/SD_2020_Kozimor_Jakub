package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learning.rest.domain.entity.enums.LessonStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long lessonId;
    @Column(name = "lesson_name")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private User teacher;
    @Column(name = "lesson_date")
    private Date lessonDate;
    @Column(name = "start_time")
    private String startTime;
    @Enumerated(EnumType.STRING)
    private LessonStatus status;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lesson_id")
    @JsonManagedReference
    private List<LessonFile> files;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "lessons_student",
            joinColumns = {@JoinColumn(name = "lesson_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @JsonBackReference
    private Set<User> students;

    public void addStudent(User user) {
        if (this.students == null) {
            this.students = new HashSet<>();
        }
        this.students.add(user);
    }

    public void addFile(LessonFile lessonFile) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.add(lessonFile);
    }

}
