package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "live_homework_files")
@Getter
@Setter
@NoArgsConstructor
public class LiveHomeworkFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long liveHomeworkFileId;
    @Column(name = "name")
    private String fileName;
    @Lob
    @Column(name = "file")
    private byte[] fileContent;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "live_homework_id")
    @JsonBackReference
    private LiveHomework liveHomework;
}
