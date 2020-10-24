package com.learning.rest.domain.dto.lesson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class LessonDto {
    private Long lessonId;
    private Long subjectId;
    private String subjectName;
    private String url;
    private String startTime;
    private List<LessonFileDto> files;
    private String status;
}
