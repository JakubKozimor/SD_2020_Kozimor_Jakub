package com.learning.aop.aspect;

import com.learning.constants.CustomConstants;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.enums.HomeworkRatedStatus;
import com.learning.rest.domain.entity.enums.HomeworkStatus;
import com.learning.rest.domain.repository.HomeworkRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class HomeworkStatusAspect {

    private final HomeworkRepository homeworkRepository;

    @Pointcut("execution( * com.learning.rest.service.impl.HomeworkServiceImpl.get*Active*(..))))")
    private void forGetHomework() {
    }


    @Pointcut("execution( * com.learning.rest.service.impl.HomeworkServiceImpl.getFiveActiveHomeworksForTeacher(..))))")
    private void forTeacherHomeworks() {
    }

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

    @Before("forTeacherHomeworks()")
    public void updateHomeworkRateStatusIfIsNoHomeworkAnswersToRate() {
        List<Homework> allHomeworks = homeworkRepository.findAll();
        allHomeworks
                .stream()
                .filter(homework -> homework.getStatus() == HomeworkStatus.LATE)
                .filter(homework -> homework.getRated() == HomeworkRatedStatus.NOT_RATED)
                .forEach(homework -> {
                    if (homework.getHomeworkAnswers() == null || homework.getHomeworkAnswers().isEmpty()) {
                        homework.setRated(HomeworkRatedStatus.RATED);
                        homeworkRepository.save(homework);
                    } else {
                        long notRatedHomeworksAnswers = homework.getHomeworkAnswers().stream()
                                .filter(homeworkAnswer -> StringUtils.isEmpty(homeworkAnswer.getGrade()))
                                .count();
                        if (notRatedHomeworksAnswers == CustomConstants.ZERO_HOMEWORKS_ANSWERS_TO_RATE) {
                            homework.setRated(HomeworkRatedStatus.RATED);
                            homeworkRepository.save(homework);
                        }
                    }
                });
    }

}
