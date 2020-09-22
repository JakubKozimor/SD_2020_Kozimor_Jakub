package com.learning.rest.service;

import com.learning.rest.domain.entity.Lesson;

public interface LessonService {
    void addLesson(Long teacherId, Lesson lesson);
}
