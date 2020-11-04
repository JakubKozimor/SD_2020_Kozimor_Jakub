package com.learning.rest.service;

import com.learning.rest.domain.dto.subject.SubjectDto;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.enums.Week;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {

    void addHomeworkToSubject(Long subjectId, Long homeworkId);

    Page<Subject> getAllSubjectsByUserId(Long userId, Pageable pageable, Week week);

    List<Subject> getFirstFiveSubjectsByUserId(Long userId, Week week);

    List<Subject> getFirstFiveSubjectsForTeacher(Long userId, Week week);

    Long addSubject(Long teacherId, SubjectDto subjectDto);

    Page<Subject> getAllSubjectsForTeacher(Long userId, Pageable pageable, Week week);

    Subject getSubjectDetails(Long subjectId);

    void updateSubject(SubjectDto subjectDto, Long subjectId);
}
