package com.learning.service.impl;

import com.learning.domain.entity.Lesson;
import com.learning.domain.entity.User;
import com.learning.domain.repository.LessonRepository;
import com.learning.domain.repository.UserRepository;
import com.learning.exception.UserNotFoundException;
import com.learning.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void addLesson(Long teacherId, Lesson lesson) {
        User teacher = userRepository.findById(teacherId).orElseThrow(UserNotFoundException::new);
        lesson.setTeacher(teacher);
        lessonRepository.save(lesson);
    }
}
