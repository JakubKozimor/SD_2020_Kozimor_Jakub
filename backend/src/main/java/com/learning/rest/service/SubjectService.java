package com.learning.rest.service;

import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.enums.Week;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {
    void addSubject(Subject subject);

    void addHomeworkToSubject(Long subjectId, Long homeworkId);

    Page<Subject> getAllSubjectsByUserId(Long userId, Pageable pageable, Week week);

    List<Subject> getFirstFiveSubjectsByUserId(Long userId);
}
