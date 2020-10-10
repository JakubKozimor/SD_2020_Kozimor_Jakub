package com.learning.rest.service.impl;

import com.learning.constants.CustomConstants;
import com.learning.exception.homework.HomeworkNotFoundException;
import com.learning.exception.subject.SubjectNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.domain.dto.homework.HomeworkDetailsDto;
import com.learning.rest.domain.dto.homework.HomeworkDto;
import com.learning.rest.domain.dto.homework.HomeworkFileDto;
import com.learning.rest.domain.dto.homework.RatedHomeworkDto;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.entity.enums.HomeworkStatus;
import com.learning.rest.domain.mapper.HomeworkMapper;
import com.learning.rest.domain.repository.HomeworkRepository;
import com.learning.rest.domain.repository.SubjectRepository;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.rest.pageable.PageHelper;
import com.learning.rest.service.HomeworkService;
import com.learning.rest.service.filter.HomeworkFilter;
import com.learning.rest.service.map.HomeworkMap;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final UserRepository userRepository;
    private final HomeworkMapper homeworkMapper;
    private final SubjectRepository subjectRepository;
    private final HomeworkFilter homeworkFilter;
    private final HomeworkMap homeworkMap;

    @Override
    public Page<Homework> getAllActiveHomeworks(Long userId, Pageable pageable) {
        List<Homework> allHomeworkList = this.getHomeworkListByUser(userId);
        List<Homework> activeHomeworks = homeworkFilter.filterByStatus(allHomeworkList, HomeworkStatus.ACTIVE);
        activeHomeworks = activeHomeworks
                .stream()
                .filter(homework -> homeworkFilter.homeworkHasUserAnswer(userId, homework))
                .sorted(Comparator.comparing(Homework::getDeadline))
                .collect(Collectors.toList());
        return (Page<Homework>) PageHelper.preparePageFromList(activeHomeworks, pageable);
    }

    private List<Homework> getHomeworkListByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Homework> allHomeworkList = new ArrayList<>();
        user.getSubjects()
                .forEach(subject -> allHomeworkList.addAll(subject.getHomeworks()));
        return allHomeworkList;
    }

    @Override
    public Page<Homework> getAllLateHomeworks(Long userId, Pageable pageable) {
        List<Homework> allHomeworkList = this.getHomeworkListByUser(userId);
        List<Homework> lateHomeworks = homeworkFilter.filterByStatus(allHomeworkList, HomeworkStatus.LATE);
        lateHomeworks = lateHomeworks
                .stream()
                .filter(homework -> homeworkFilter.homeworkHasUserAnswer(userId, homework))
                .sorted(Comparator.comparing(Homework::getDeadline).reversed())
                .collect(Collectors.toList());
        return (Page<Homework>) PageHelper.preparePageFromList(lateHomeworks, pageable);
    }

    @Override
    public Page<Homework> getAllDoneHomeworks(Long userId, Pageable pageable) {
        List<Homework> allHomeworkList = this.getHomeworkListByUser(userId);
        allHomeworkList = allHomeworkList
                .stream()
                .filter(homework -> !homeworkFilter.homeworkHasUserAnswer(userId, homework))
                .filter(homework -> homeworkFilter.checkGradePresent(userId, homework))
                .sorted(Comparator.comparing(Homework::getDeadline).reversed())
                .collect(Collectors.toList());
        return (Page<Homework>) PageHelper.preparePageFromList(allHomeworkList, pageable);
    }

    @Override
    public Page<RatedHomeworkDto> getAllRatedHomeworks(Long userId, Pageable pageable) {
        List<Homework> allHomeworkList = this.getHomeworkListByUser(userId);
        List<RatedHomeworkDto> ratedHomeworks = allHomeworkList
                .stream()
                .filter(homework -> !homeworkFilter.homeworkHasUserAnswer(userId, homework))
                .filter(homework -> !homeworkFilter.checkGradePresent(userId, homework))
                .sorted(Comparator.comparing(Homework::getDeadline).reversed())
                .map(homework -> homeworkMap.mapToRatedHomeworkDtoAndAssignGrade(userId, homework))
                .collect(Collectors.toList());
        return (Page<RatedHomeworkDto>) PageHelper.preparePageFromList(ratedHomeworks, pageable);
    }

    @Override
    public List<Homework> getFiveActiveHomeworks(Long userId) {
        List<Homework> allHomeworkList = this.getHomeworkListByUser(userId);
        List<Homework> activeHomeworks = homeworkFilter.filterByStatus(allHomeworkList, HomeworkStatus.ACTIVE);
        return activeHomeworks
                .stream()
                .filter(homework -> homeworkFilter.homeworkHasUserAnswer(userId, homework))
                .sorted(Comparator.comparing(Homework::getDeadline))
                .limit(CustomConstants.FIVE_HOMEWORKS)
                .collect(Collectors.toList());

    }

    @Override
    public HomeworkDetailsDto getHomeworkDetails(Long homeworkId) {
        Homework homework = homeworkRepository.findById(homeworkId).orElseThrow(HomeworkNotFoundException::new);
        return homeworkMapper.toHomeworkDetailsDto(homework);
    }

    @Override
    public void createHomework(HomeworkDto homeworkDto, Long subjectId) {
        Homework homework = homeworkMapper.toHomework(homeworkDto);
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
        List<HomeworkFileDto> homeworkFilesDto = homeworkDto.getFiles();
        if (homeworkFilesDto != null) {
            homeworkFilesDto
                    .stream()
                    .map(homeworkMap::mapToHomeworkFile)
                    .forEach(homework::addFile);
        }
        homework.setSubject(subject);
        homeworkRepository.save(homework);
    }
}
