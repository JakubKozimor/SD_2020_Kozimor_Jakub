package com.learning.rest.service.impl;

import com.learning.rest.domain.entity.Lesson;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.repository.LessonRepository;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.service.LessonService;
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
