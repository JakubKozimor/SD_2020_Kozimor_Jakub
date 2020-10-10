package com.learning.rest.domain.dto.homework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HomeworkFileDto {
    private Long homeworkFileId;
    private String fileName;
    private String fileContent;
}
