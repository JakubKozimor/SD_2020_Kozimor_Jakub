package com.learning.rest.domain.dto.lesson;

import com.learning.rest.domain.entity.LessonFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class LessonDetailsDto {
    private Long lessonId;
    private Long subjectId;
    private String subjectName;
    private String url;
    private String startTime;
    private List<LessonFile> files;
    private String status;
}
