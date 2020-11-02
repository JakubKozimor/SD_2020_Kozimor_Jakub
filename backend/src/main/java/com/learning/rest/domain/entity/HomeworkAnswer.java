package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "homework_answer")
@Getter
@Setter
@NoArgsConstructor
public class HomeworkAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homework_answer_id")
    private Long homeworkAnswerId;
    @Column(name = "message")
    private String message;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "homework")
    @JsonBackReference
    private Homework homework;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student")
    @JsonBackReference
    private User student;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "homework_answer_id")
    private List<HomeworkAnswerFile> files;
        private String grade;


    public void addFile(HomeworkAnswerFile homeworkAnswerFile) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.add(homeworkAnswerFile);
    }
}
