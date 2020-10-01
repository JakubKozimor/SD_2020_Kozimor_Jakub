package com.learning.rest.service.impl;

import com.learning.constants.CustomConstants;
import com.learning.exception.homework.HomeworkNotFoundException;
import com.learning.exception.subject.SubjectNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.base64.Base64Helper;
import com.learning.rest.domain.dto.HomeworkDetailsDto;
import com.learning.rest.domain.dto.HomeworkDto;
import com.learning.rest.domain.dto.HomeworkFileDto;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.HomeworkFile;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.entity.enums.HomeworkStatus;
import com.learning.rest.domain.mapper.HomeworkFileMapper;
import com.learning.rest.domain.mapper.HomeworkMapper;
import com.learning.rest.domain.repository.HomeworkRepository;
import com.learning.rest.domain.repository.SubjectRepository;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.rest.pageable.PageHelper;
import com.learning.rest.service.HomeworkService;
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
    private final HomeworkFileMapper homeworkFileMapper;
    private final SubjectRepository subjectRepository;

    @Override
    public Page<Homework> getAllActiveHomeworks(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Homework> allHomeworkList = this.getHomeworksFromSubjects(user);
        List<Homework> activeHomeworks = this.filterByStatus(allHomeworkList, HomeworkStatus.ACTIVE);
        return (Page<Homework>) PageHelper.preparePageFromList(activeHomeworks, pageable);
    }

    @Override
    public List<Homework> getFiveActiveHomework(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Homework> allHomeworkList = this.getHomeworksFromSubjects(user);
        List<Homework> activeHomeworks = this.filterByStatus(allHomeworkList, HomeworkStatus.ACTIVE);
        return activeHomeworks
                .stream()
                .sorted(Comparator.comparing(Homework::getDeadline))
                .limit(CustomConstants.FIVE_HOMEWORKS)
                .collect(Collectors.toList());

    }

    private List<Homework> getHomeworksFromSubjects(User user) {
        List<Homework> allHomeworkList = new ArrayList<>();
        user.getSubjects()
                .forEach(subject -> allHomeworkList.addAll(subject.getHomeworks()));
        return allHomeworkList;
    }

    private List<Homework> filterByStatus(List<Homework> allHomeworkList, HomeworkStatus homeworkStatus) {
        return allHomeworkList
                .stream()
                .filter(homework -> homework.getStatus() == homeworkStatus)
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
        homeworkDto.getFiles()
                .stream()
                .map(this::mapToHomeworkFile)
                .forEach(homework::addFile);
        homework.setSubject(subject);
        homeworkRepository.save(homework);
    }

    private HomeworkFile mapToHomeworkFile(HomeworkFileDto homeworkFileDto) {
        HomeworkFile homeworkFile = homeworkFileMapper.toHomeworkFile(homeworkFileDto);
        String fileContentInBase64 = Base64Helper.getContentFromFileInBase64(homeworkFileDto.getFileContent());
        byte[] decodedFile = Base64Helper.decodeFileFromBase64(fileContentInBase64);
        homeworkFile.setFileContent(decodedFile);
        return homeworkFile;
    }
}
