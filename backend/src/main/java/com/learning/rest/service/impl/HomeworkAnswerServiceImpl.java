package com.learning.rest.service.impl;

import com.learning.exception.homework.HomeworkNotFoundException;
import com.learning.exception.homeworkAnswer.HomeworkAnswerNotFoundException;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDetailsDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDto;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.HomeworkAnswer;
import com.learning.rest.domain.mapper.HomeworkAnswerMapper;
import com.learning.rest.domain.repository.HomeworkAnswerFileRepository;
import com.learning.rest.domain.repository.HomeworkAnswerRepository;
import com.learning.rest.domain.repository.HomeworkRepository;
import com.learning.rest.service.HomeworkAnswerService;
import com.learning.rest.service.map.HomeworkAnswerMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void addHomeworkAnswer(HomeworkAnswerDto homeworkAnswerDto) {
        HomeworkAnswer homeworkAnswer = homeworkAnswerMap.mapFromHomeworkAnswerDtoToHomeworkAnswer(homeworkAnswerDto);
        homeworkAnswerRepository.save(homeworkAnswer);
    }

    @Override
    public void updateHomeworkAnswer(HomeworkAnswerDto homeworkAnswerDto) {
        HomeworkAnswer homeworkAnswer = homeworkAnswerMap.mapFromHomeworkAnswerDtoToHomeworkAnswer(homeworkAnswerDto);
        homeworkAnswer.getFiles()
                .forEach(homeworkAnswerFile -> homeworkAnswerFile.setHomeworkAnswerFileId(null));
        homeworkAnswerRepository.save(homeworkAnswer);
    }

}
