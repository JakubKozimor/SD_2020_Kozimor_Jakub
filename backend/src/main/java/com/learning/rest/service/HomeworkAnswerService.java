package com.learning.rest.service;

import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDetailsDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerUserDetailsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HomeworkAnswerService {
    void addHomeworkAnswer(HomeworkAnswerDto homework);

    void updateHomeworkAnswer(HomeworkAnswerDto homework);

    HomeworkAnswerDetailsDto getHomeworkAnswerDetails(Long homeworkId, Long userId);

    Page<HomeworkAnswerUserDetailsDto> getAllHomeworkAnswersWithNoGrade(Long homeworkId, Pageable pageable);

    Page<HomeworkAnswerUserDetailsDto> getAllHomeworkAnswersWithGrade(Long homeworkId, Pageable pageable);

    HomeworkAnswerDetailsDto getHomeworkAnswerDetailsByAnswerId(Long answerId);

    void addGrade(Long homeworkAnswerId, String grade);
}
