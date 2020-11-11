package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "live_homeworks")
@Getter
@Setter
@NoArgsConstructor
public class LiveHomework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "live_homework_id")
    private Long liveHomeworkId;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "live_homework_id")
    private List<LiveHomeworkFile> files;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "lesson_id")
    @JsonBackReference
    private Lesson lesson;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "live_homework")
    private List<LiveHomeworkAnswer> liveHomeworkAnswer;

    public void addFile(LiveHomeworkFile homeworkFile) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.add(homeworkFile);
    }
}
