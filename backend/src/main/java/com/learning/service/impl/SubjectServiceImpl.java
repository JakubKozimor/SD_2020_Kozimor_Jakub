package com.learning.service.impl;

import com.learning.constants.Constants;
import com.learning.domain.entity.Homework;
import com.learning.domain.entity.Subject;
import com.learning.domain.entity.User;
import com.learning.domain.repository.HomeworkRepository;
import com.learning.domain.repository.SubjectRepository;
import com.learning.domain.repository.UserRepository;
import com.learning.exception.HomeworkNotFoundException;
import com.learning.exception.SubjectNotFoundException;
import com.learning.exception.UserNotFoundException;
import com.learning.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final HomeworkRepository homeworkRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void addSubject(Subject subject) {
        //TODO to change
        User user = userRepository.findById(3L).orElseThrow(UserNotFoundException::new);
        subject.setTeacher(user);
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

    @Override
    public List<Subject> getAllSubjectsByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return user.getSubjects()
                .stream()
                .sorted(Comparator.comparing(o -> Integer.valueOf(o.getStartTime().replaceAll("[^0-9]+", ""))))
                .collect(Collectors.toList());
    }

    @Override
    public List<Subject> getFirstFiveSubjectsByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return user.getSubjects()
                .stream()
                .filter(subject -> LocalDate.now().getDayOfWeek().getValue() <= subject.getDay().ordinal() + 1)
                .filter(subject -> {
                    if (LocalDate.now().getDayOfWeek().getValue() == subject.getDay().ordinal() + 1) {
                        return filterByTime(subject);
                    } else return true;
                })
                .limit(5)
                .sorted((o1, o2) -> {
                    if (o1.getDay() == o2.getDay()) {
                        return Integer.valueOf(o1.getStartTime().replaceAll("[^0-9]+", "")).compareTo(Integer.valueOf(o2.getStartTime().replaceAll("[^0-9]+", "")));
                    } else return 1;
                })
                .collect(Collectors.toList());
    }

    private boolean filterByTime(Subject subject) {
        int subjectTime = Integer.parseInt(subject.getStartTime().replaceAll("[^0-9]+", ""));
        int minute = LocalTime.now().getMinute();
        String currentTimeString;
        if (minute < Constants.TENMINUTE)
            currentTimeString = LocalTime.now().getHour() + ":0" + LocalTime.now().getMinute();
        else currentTimeString = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
        int currentTimeValue = Integer.parseInt(currentTimeString.replaceAll("[^0-9]+", ""));
        return subjectTime > currentTimeValue;
    }
}
