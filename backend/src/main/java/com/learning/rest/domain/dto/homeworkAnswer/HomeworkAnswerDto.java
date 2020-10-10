package com.learning.rest.domain.dto.homeworkAnswer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class HomeworkAnswerDto {
    private Long homeworkAnswerId;
    private String message;
    private List<HomeworkAnswerFileDto> files;
    private Long homeworkId;
    private Long studentId;
}
