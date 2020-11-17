package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learning.rest.domain.entity.enums.HomeworkRatedStatus;
import com.learning.rest.domain.entity.enums.HomeworkStatus;
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
    private Long homeworkId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "deadline")
    private Date deadline;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "homework_id")
    private List<HomeworkFile> files;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    private Subject subject;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private HomeworkStatus status;
    @Column(name = "rated")
    @Enumerated(EnumType.STRING)
    private HomeworkRatedStatus rated;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "homework")
    private List<HomeworkAnswer> homeworkAnswers;

    public void addFile(HomeworkFile homeworkFile) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.add(homeworkFile);
    }
}
