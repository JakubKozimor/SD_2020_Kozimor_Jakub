package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "live_homework_answer_files")
@Getter
@Setter
@NoArgsConstructor
public class LiveHomeworkAnswerFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long liveHomeworkAnswerFileId;
    @Column(name = "name")
    private String fileName;
    @Lob
    @Column(name = "file")
    private byte[] fileContent;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "live_homework_answer_id")
    @JsonBackReference
    private LiveHomeworkAnswer liveHomeworkAnswer;

}
