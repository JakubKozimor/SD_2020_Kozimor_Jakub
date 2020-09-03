package com.learning.service;

import com.learning.domain.entity.Lesson;

public interface LessonService {
    void addLesson(Long teacherId, Lesson lesson);
}
