package com.learning.service.impl;

import com.learning.domain.entity.Homework;
import com.learning.domain.entity.Subject;
import com.learning.domain.repository.HomeworkRepository;
import com.learning.domain.repository.SubjectRepository;
import com.learning.exception.HomeworkNotFoundException;
import com.learning.exception.SubjectNotFoundException;
import com.learning.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final HomeworkRepository homeworkRepository;

    @Override
    @Transactional
    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    @Transactional
    public void addHomeworkToSubject(Long subjectId, Long homeworkId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
        Homework homework = homeworkRepository.findById(homeworkId).orElseThrow(HomeworkNotFoundException::new);
        subject.addHomework(homework);
        subjectRepository.save(subject);
    }
}
