package com.learning.rest.service;

import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User getTeacherById(Long teacherId);

    void addUserToSubject(Long userId, Long subjectId);

    void addUserToLesson(Long userId, Long lessonId);
}
