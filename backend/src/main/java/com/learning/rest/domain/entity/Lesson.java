package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learning.rest.domain.entity.enums.LessonStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @JsonBackReference
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @Column(name = "url")
    private String url;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private User teacher;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lesson_id")
    @JsonManagedReference
    private List<LiveHomework> homeworks;
    @Enumerated(EnumType.STRING)
    private LessonStatus status;
    @OneToMany(cascade = CascadeType.ALL)
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
    private List<User> students;

    public void addStudent(User user) {
        if (this.students == null) {
            this.students = new ArrayList<>();
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
