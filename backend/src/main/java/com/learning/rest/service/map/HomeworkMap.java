package com.learning.rest.service.map;

import com.learning.rest.base64.Base64Helper;
import com.learning.rest.domain.dto.HomeworkFileDto;
import com.learning.rest.domain.dto.RatedHomeworkDto;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.HomeworkAnswer;
import com.learning.rest.domain.entity.HomeworkFile;
import com.learning.rest.domain.mapper.HomeworkFileMapper;
import com.learning.rest.domain.mapper.HomeworkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HomeworkMap {

    private final HomeworkMapper homeworkMapper;
    private final HomeworkFileMapper homeworkFileMapper;

    public RatedHomeworkDto mapToRatedHomeworkDtoAndAssignGrade(Long userId, Homework homework) {
        RatedHomeworkDto ratedHomeworkDto = homeworkMapper.toRatedHomework(homework);
        List<HomeworkAnswer> homeworkAnswers = homework.getHomeworkAnswers();
        Optional<HomeworkAnswer> homeworkAnswerOptional = homeworkAnswers
                .stream()
                .filter(homeworkAnswer -> homeworkAnswer.getStudent().getUserId().equals(userId))
                .findFirst();
        homeworkAnswerOptional.ifPresent(homeworkAnswer -> ratedHomeworkDto.setGrade(homeworkAnswer.getGrade()));
        return ratedHomeworkDto;
    }

    public HomeworkFile mapToHomeworkFile(HomeworkFileDto homeworkFileDto) {
        HomeworkFile homeworkFile = homeworkFileMapper.toHomeworkFile(homeworkFileDto);
        String fileContentInBase64 = Base64Helper.getContentFromFileInBase64(homeworkFileDto.getFileContent());
        byte[] decodedFile = Base64Helper.decodeFileFromBase64(fileContentInBase64);
        homeworkFile.setFileContent(decodedFile);
        return homeworkFile;
    }
}
