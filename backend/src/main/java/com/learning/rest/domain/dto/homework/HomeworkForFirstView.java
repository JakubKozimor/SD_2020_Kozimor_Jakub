package com.learning.rest.domain.dto.homework;

import com.learning.rest.domain.entity.HomeworkFile;
import com.learning.rest.domain.entity.enums.HomeworkStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class HomeworkForFirstView {
    private Long homeworkId;
    private String title;
    private String description;
    private Date deadline;
    private List<HomeworkFile> files;
    private String subject;
    private HomeworkStatus status;
}
