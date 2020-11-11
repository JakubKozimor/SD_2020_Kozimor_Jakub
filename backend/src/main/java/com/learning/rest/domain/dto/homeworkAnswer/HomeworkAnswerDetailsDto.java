package com.learning.rest.domain.dto.homeworkAnswer;

import com.learning.rest.domain.entity.HomeworkAnswerFile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class HomeworkAnswerDetailsDto {
    private Long homeworkAnswerId;
    private String message;
    private String grade;
    private String comment;
    private List<HomeworkAnswerFile> files;
}
