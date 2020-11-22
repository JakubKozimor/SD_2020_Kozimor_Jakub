package com.learning.aop.aspect;

import com.learning.rest.domain.repository.HomeworkAnswerRepository;
import com.learning.rest.domain.repository.HomeworkFileRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class HomeworkFilesAspect {

    private final HomeworkFileRepository homeworkFileRepository;

    @Before("execution(* com.learning.rest.service.impl.HomeworkServiceImpl.getHomeworkDetails(..))")
    public void removeNotUsedHomeworkAnswerFiles() {
        homeworkFileRepository.findAll()
                .forEach(homeworkFile -> {
                    if (homeworkFile.getHomework() == null)
                        homeworkFileRepository.delete(homeworkFile);
                });
    }
}