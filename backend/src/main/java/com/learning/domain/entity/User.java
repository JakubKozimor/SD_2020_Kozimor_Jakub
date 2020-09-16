package com.learning.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @NotEmpty(message = "Roles must be not null")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "lessons_student",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id")}
    )
    @JsonManagedReference
    private Set<Lesson> lessons;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "subject_student",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )
    @JsonBackReference
    private Set<Subject> subjects;

    public void addSubject(Subject subject) {
        if (subjects == null) {
            subjects = new HashSet<>();
        }
        subjects.add(subject);
    }

    public void addLesson(Lesson lesson) {
        if (lessons == null) {
            lessons = new HashSet<>();
        }
        lessons.add(lesson);
    }
}
