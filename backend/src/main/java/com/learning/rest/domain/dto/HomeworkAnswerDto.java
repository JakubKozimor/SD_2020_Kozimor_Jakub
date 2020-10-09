package com.learning.rest.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class HomeworkAnswerDto {
    private String message;
    private List<HomeworkAnswerFileDto> files;
    private Long homeworkId;
    private Long studentId;
}
