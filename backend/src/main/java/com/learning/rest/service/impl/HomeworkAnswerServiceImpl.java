package com.learning.rest.service.impl;

import com.learning.exception.homework.HomeworkNotFoundException;
import com.learning.exception.homeworkAnswer.HomeworkAnswerNotFoundException;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDetailsDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerUserDetailsDto;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.HomeworkAnswer;
import com.learning.rest.domain.entity.HomeworkAnswerFile;
import com.learning.rest.domain.mapper.HomeworkAnswerMapper;
import com.learning.rest.domain.repository.HomeworkAnswerRepository;
import com.learning.rest.domain.repository.HomeworkRepository;
import com.learning.rest.service.HomeworkAnswerService;
import com.learning.rest.service.map.HomeworkAnswerMap;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeworkAnswerServiceImpl implements HomeworkAnswerService {

    private final HomeworkAnswerMapper homeworkAnswerMapper;
    private final HomeworkRepository homeworkRepository;
    private final HomeworkAnswerRepository homeworkAnswerRepository;
    private final HomeworkAnswerMap homeworkAnswerMap;

    @Override
    public HomeworkAnswerDetailsDto getHomeworkAnswerDetails(Long homeworkId, Long userId) {
        Homework homework = homeworkRepository.findById(homeworkId).orElseThrow(HomeworkNotFoundException::new);
        List<HomeworkAnswer> homeworkAnswers = homework.getHomeworkAnswers();
        Optional<HomeworkAnswer> homeworkAnswerOptional = homeworkAnswers.stream()
                .filter(homeworkAnswer -> homeworkAnswer.getStudent().getUserId().equals(userId))
                .findFirst();
        HomeworkAnswer homeworkAnswer = homeworkAnswerOptional.orElseThrow(HomeworkAnswerNotFoundException::new);
        return homeworkAnswerMapper.toHomeworkAnswerDetailsDto(homeworkAnswer);
    }

    @Override
    public HomeworkAnswerDetailsDto getHomeworkAnswerDetailsByAnswerId(Long answerId) {
        HomeworkAnswer homeworkAnswer = homeworkAnswerRepository.findById(answerId).orElseThrow(HomeworkAnswerNotFoundException::new);
        return homeworkAnswerMapper.toHomeworkAnswerDetailsDto(homeworkAnswer);
    }

    @Override
    public void addHomeworkAnswer(HomeworkAnswerDto homeworkAnswerDto) {
        HomeworkAnswer homeworkAnswer = homeworkAnswerMap.mapFromHomeworkAnswerDtoToHomeworkAnswer(homeworkAnswerDto);
        homeworkAnswerRepository.save(homeworkAnswer);
    }

    @Override
    public void updateHomeworkAnswer(HomeworkAnswerDto homeworkAnswerDto) {
        HomeworkAnswer homeworkAnswer = homeworkAnswerMap.mapFromHomeworkAnswerDtoToHomeworkAnswer(homeworkAnswerDto);
        List<HomeworkAnswerFile> homeworkAnswerFiles = homeworkAnswer.getFiles();
        if (homeworkAnswerFiles != null && !homeworkAnswerFiles.isEmpty()) {
            homeworkAnswer.getFiles()
                    .forEach(homeworkAnswerFile -> homeworkAnswerFile.setHomeworkAnswerFileId(null));
        }
        homeworkAnswerRepository.save(homeworkAnswer);
    }

    @Override
    public Page<HomeworkAnswerUserDetailsDto> getAllHomeworkAnswersWithNoGrade(Long homeworkId, Pageable pageable) {
        Homework homework = homeworkRepository.findById(homeworkId).orElseThrow(HomeworkAnswerNotFoundException::new);
        Page<HomeworkAnswer> allAnswersByHomework = homeworkAnswerRepository.findAllByHomeworkAndGradeIsNull(homework, pageable);
        List<HomeworkAnswerUserDetailsDto> homeworkAnswerUserDetailsDtoList = allAnswersByHomework.stream()
                .map(homeworkAnswerMapper::toHomeworkAnswerUserDetailsDto)
                .collect(Collectors.toList());
        return new PageImpl<>(homeworkAnswerUserDetailsDtoList, pageable, allAnswersByHomework.getTotalElements());
    }

    @Override
    public Page<HomeworkAnswerUserDetailsDto> getAllHomeworkAnswersWithGrade(Long homeworkId, Pageable pageable) {
        Homework homework = homeworkRepository.findById(homeworkId).orElseThrow(HomeworkAnswerNotFoundException::new);
        Page<HomeworkAnswer> allAnswersByHomework = homeworkAnswerRepository.findAllByHomeworkAndGradeIsNotNull(homework, pageable);
        List<HomeworkAnswerUserDetailsDto> homeworkAnswerUserDetailsDtoList = allAnswersByHomework.stream()
                .map(homeworkAnswerMapper::toHomeworkAnswerUserDetailsDto)
                .collect(Collectors.toList());
        return new PageImpl<>(homeworkAnswerUserDetailsDtoList, pageable, allAnswersByHomework.getTotalElements());
    }

    @Override
    public void addGrade(Long homeworkAnswerId, String grade, String comment) {
        HomeworkAnswer homeworkAnswer = homeworkAnswerRepository.findById(homeworkAnswerId).orElseThrow(HomeworkAnswerNotFoundException::new);
        homeworkAnswer.setGrade(grade);
        homeworkAnswer.setComment(comment);
        homeworkAnswerRepository.save(homeworkAnswer);
    }
}
