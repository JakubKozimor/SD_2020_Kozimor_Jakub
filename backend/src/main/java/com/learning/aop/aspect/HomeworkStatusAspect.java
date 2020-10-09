package com.learning.aop.aspect;

import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.enums.HomeworkStatus;
import com.learning.rest.domain.repository.HomeworkRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class HomeworkStatusAspect {

    private final HomeworkRepository homeworkRepository;

    @Pointcut("execution( * com.learning.rest.service.impl.HomeworkServiceImpl.get*Active*(..))))")
    private void forGetHomework() {}

    @Before("forGetHomework()")
    public void updateHomeworkStatusIfDeadlineIsGone() {
        List<Homework> allHomeworks = homeworkRepository.findAll();
        allHomeworks
                .stream()
                .filter(homework -> homework.getStatus() == HomeworkStatus.ACTIVE)
                .filter(homework -> homework.getDeadline().toLocalDate().isBefore(LocalDate.now()))
                .forEach(homework -> {
                    homework.setStatus(HomeworkStatus.LATE);
                    homeworkRepository.save(homework);
                });
    }

}
