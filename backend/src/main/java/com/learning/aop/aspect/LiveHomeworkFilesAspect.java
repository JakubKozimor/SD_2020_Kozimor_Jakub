package com.learning.aop.aspect;

import com.learning.rest.domain.repository.LiveHomeworkFileRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LiveHomeworkFilesAspect {

    private final LiveHomeworkFileRepository liveHomeworkFileRepository;

    @Before("execution(* com.learning.rest.service.impl.LiveHomeworkServiceImpl.getLiveHomeworkDetails(..))" +
            "|| execution(* com.learning.rest.service.impl.LiveHomeworkServiceImpl.getAllLiveHomeworks(..))")
    public void removeNotUsedLiveHomeworkAnswerFiles() {
        liveHomeworkFileRepository.findAll()
                .forEach(liveHomeworkFile -> {
                    if (liveHomeworkFile.getLiveHomework() == null)
                        liveHomeworkFileRepository.delete(liveHomeworkFile);
                });
    }
}