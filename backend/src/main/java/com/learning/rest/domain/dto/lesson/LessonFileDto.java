package com.learning.rest.domain.dto.lesson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LessonFileDto {
    private Long classesFileId;
    private String fileName;
    private String fileContent;
}
