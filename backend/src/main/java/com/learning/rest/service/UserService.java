package com.learning.rest.service;

import com.learning.rest.domain.entity.User;

public interface UserService {
    User getTeacherById(Long teacherId);

    void addUserToSubject(Long userId, Long subjectId);

    void addUserToLesson(Long userId, Long lessonId);

    String getUserFirstNameAndLastNameById(Long userId);
}
