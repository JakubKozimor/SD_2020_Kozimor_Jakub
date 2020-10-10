package com.learning.rest.domain.dto.homework;

import com.learning.rest.domain.entity.HomeworkFile;
import com.learning.rest.domain.entity.enums.HomeworkStatus;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;


@NoArgsConstructor
@Data
public class HomeworkDetailsDto {
    private Long homeworkId;
    private String title;
    private String description;
    private String deadline;
    private String subject;
    @Enumerated(EnumType.STRING)
    private HomeworkStatus status;
    private List<HomeworkFile> files;
}
