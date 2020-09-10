package com.learning.service;

import com.learning.domain.entity.Homework;
import com.learning.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User getTeacherById(Long id);

    void addUserToSubject(Long userId, Long subjectId);

    void addUserToLesson(Long userId, Long lessonId);

    Page<Homework> getAllHomeworks(Long id, Pageable pageable);
}
