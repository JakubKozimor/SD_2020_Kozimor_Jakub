package com.learning.rest.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HomeworkAnswerFileDto {
    private Long homeworkAnswerFileId;
    private String fileName;
    private String fileContent;
}
