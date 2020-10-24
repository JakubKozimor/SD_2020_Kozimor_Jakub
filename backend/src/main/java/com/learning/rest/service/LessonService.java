package com.learning.rest.service;

import com.learning.rest.domain.dto.lesson.LessonDetailsDto;
import com.learning.rest.domain.dto.lesson.LessonDto;

import java.util.List;

public interface LessonService {

    Long addLesson(Long teacherId, LessonDto lessonDto);

    LessonDetailsDto geLessonDetails(Long lessonId);

    List<LessonDetailsDto> getLiveLesson(Long userId);
}
