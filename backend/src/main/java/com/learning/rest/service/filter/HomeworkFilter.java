package com.learning.rest.service.filter;

import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.HomeworkAnswer;
import com.learning.rest.domain.entity.enums.HomeworkStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HomeworkFilter {

    public boolean homeworkHasUserAnswer(Long userId, Homework homework) {
        HomeworkAnswer userAnswer = this.getUserAnswer(userId, homework);
        return userAnswer.getHomeworkAnswerId() == null;
    }

    public boolean checkGradePresent(Long userId, Homework homework) {
        String grade = this.getUserAnswer(userId, homework).getGrade();
        return StringUtils.isEmpty(grade);
    }

    private HomeworkAnswer getUserAnswer(Long userId, Homework homework) {
        List<HomeworkAnswer> homeworkAnswers = homework.getHomeworkAnswers();
        if (homeworkAnswers != null) {
            Optional<HomeworkAnswer> homeworkAnswerOptional = homeworkAnswers
                    .stream()
                    .filter(homeworkAnswer -> homeworkAnswer.getStudent().getUserId().equals(userId))
                    .findFirst();
            if (homeworkAnswerOptional.isPresent())
                return homeworkAnswerOptional.get();
        }
        return new HomeworkAnswer();
    }

    public List<Homework> filterByStatus(List<Homework> allHomeworkList, HomeworkStatus homeworkStatus) {
        return allHomeworkList
                .stream()
                .filter(homework -> homework.getStatus() == homeworkStatus)
                .collect(Collectors.toList());
    }

    public List<Homework> filterBySubject(List<Homework> allHomeworkList, Long subjectId) {
        return allHomeworkList
                .stream()
                .filter(homework -> homework.getSubject().getSubjectId().equals(subjectId))
                .collect(Collectors.toList());
    }
}
