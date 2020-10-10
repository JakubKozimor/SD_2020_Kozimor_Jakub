package com.learning.aop.aspect;

import com.learning.rest.domain.repository.HomeworkAnswerFileRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class HomeworkAnswerFilesAspect {

    private final HomeworkAnswerFileRepository homeworkAnswerFileRepository;

    @Before("execution(* com.learning.rest.service.impl.HomeworkAnswerServiceImpl.getHomeworkAnswerDetails(..))")
    public void removeNotUsedHomeworkAnswerFiles() {
        homeworkAnswerFileRepository.findAll()
                .forEach(homeworkAnswerFile -> {
                    if (homeworkAnswerFile.getHomeworkAnswer() == null)
                        homeworkAnswerFileRepository.delete(homeworkAnswerFile);
                });
    }
}
