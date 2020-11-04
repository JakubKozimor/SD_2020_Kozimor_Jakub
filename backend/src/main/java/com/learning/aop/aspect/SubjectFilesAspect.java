package com.learning.aop.aspect;

import com.learning.rest.domain.repository.SubjectFileRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class SubjectFilesAspect {

    private final SubjectFileRepository subjectFileRepository;

    @Before("execution(* com.learning.rest.service.impl.SubjectServiceImpl.getAllSubjects*(..)) || execution(* com.learning.rest.service.impl.SubjectServiceImpl.getSubjectDetails(..))")
    @After("execution(* com.learning.rest.service.impl.SubjectServiceImpl.updateSubject(..))")
    public void removeNotUsedHomeworkAnswerFiles() {
        subjectFileRepository.findAll()
                .forEach(subjectFile -> {
                    if (subjectFile.getSubject() == null)
                        subjectFileRepository.delete(subjectFile);
                });
    }
}
