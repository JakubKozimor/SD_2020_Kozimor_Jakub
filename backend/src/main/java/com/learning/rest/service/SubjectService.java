package com.learning.rest.service;

import com.learning.rest.domain.entity.Subject;

import java.util.List;

public interface SubjectService {
    void addSubject(Subject subject);

    void addHomeworkToSubject(Long subjectId, Long homeworkId);

    List<Subject> getAllSubjectsByUserId(Long id);

    List<Subject> getFirstFiveSubjectsByUserId(Long id);
}
