package com.learning.service;

import com.learning.domain.entity.Subject;
import com.learning.domain.entity.User;

import java.util.Set;

public interface UserService {
    User getTeacherById(Long id);
    Set<Subject> getAllSubjectsByUserId(Long id);
    void addUserToSubject(Long userId, Long subjectId);
    void addUserToLesson(Long userId, Long lessonId);
}
