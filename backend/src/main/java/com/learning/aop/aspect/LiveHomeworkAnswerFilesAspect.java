package com.learning.aop.aspect;

import com.learning.rest.domain.repository.LiveHomeworkAnswerFileRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LiveHomeworkAnswerFilesAspect {

    private final LiveHomeworkAnswerFileRepository liveHomeworkAnswerFileRepository;

    @Before("execution(* com.learning.rest.service.impl.LiveHomeworkAnswerServiceImpl.getAllAnswersByLiveHomeworkId(..))" +
            "|| execution(* com.learning.rest.service.impl.LiveHomeworkAnswerServiceImpl.getLiveHomeworkAnswerDetails(..))")
    public void removeNotUsedHomeworkAnswerFiles() {
        liveHomeworkAnswerFileRepository.findAll()
                .forEach(liveHomeworkAnswerFile -> {
                    if (liveHomeworkAnswerFile.getLiveHomeworkAnswer() == null)
                        liveHomeworkAnswerFileRepository.delete(liveHomeworkAnswerFile);
                });
    }
}