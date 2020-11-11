package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "live_homework_answer")
@Getter
@Setter
@NoArgsConstructor
public class LiveHomeworkAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "live_homework_answer_id")
    private Long liveHomeworkAnswerId;
    @Column(name = "message")
    private String message;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "live_homework")
    @JsonBackReference
    private LiveHomework liveHomework;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student")
    @JsonBackReference
    private User student;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "live_homework_answer_id")
    private List<LiveHomeworkAnswerFile> files;

    public void addFile(LiveHomeworkAnswerFile homeworkAnswerFile) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.add(homeworkAnswerFile);
    }
}
