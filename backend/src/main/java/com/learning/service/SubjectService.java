package com.learning.service;

import com.learning.domain.entity.Subject;

public interface SubjectService {
    void addSubject(Subject subject);

    void addHomeworkToSubject(Long subjectId, Long homeworkId);
}
