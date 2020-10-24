package com.learning.rest.domain.dto.lesson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class LessonDto {
    Long classesId;
    Long subjectId;
    String subjectName;
    String url;
    String startTime;
    List<ClassesFileDto> files;
    String status;
}
