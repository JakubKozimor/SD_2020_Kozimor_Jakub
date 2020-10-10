package com.learning.rest.service;

import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDetailsDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDto;

public interface HomeworkAnswerService {
    void addHomeworkAnswer(HomeworkAnswerDto homework);

    void updateHomeworkAnswer(HomeworkAnswerDto homework);

    HomeworkAnswerDetailsDto getHomeworkAnswerDetails(Long homeworkId, Long userId);
}
