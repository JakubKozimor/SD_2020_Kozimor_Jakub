package com.learning.rest.service.map;

import com.learning.exception.homework.HomeworkNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.base64.Base64Helper;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerFileDto;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.HomeworkAnswer;
import com.learning.rest.domain.entity.HomeworkAnswerFile;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.mapper.HomeworkAnswerFileMapper;
import com.learning.rest.domain.mapper.HomeworkAnswerMapper;
import com.learning.rest.domain.repository.HomeworkRepository;
import com.learning.rest.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HomeworkAnswerMap {

    private final HomeworkAnswerMapper homeworkAnswerMapper;
    private final HomeworkAnswerFileMapper homeworkAnswerFileMapper;
    private final UserRepository userRepository;
    private final HomeworkRepository homeworkRepository;

    public HomeworkAnswer mapFromHomeworkAnswerDtoToHomeworkAnswer(HomeworkAnswerDto homeworkAnswerDto) {
        HomeworkAnswer homeworkAnswer = homeworkAnswerMapper.toHomeworkAnswer(homeworkAnswerDto);
        homeworkAnswerDto.getFiles()
                .stream()
                .map(this::mapToHomeworkAnswerFile)
                .forEach(homeworkAnswer::addFile);
        User student = userRepository.findById(homeworkAnswerDto.getStudentId()).orElseThrow(UserNotFoundException::new);
        Homework homework = homeworkRepository.findById(homeworkAnswerDto.getHomeworkId()).orElseThrow(HomeworkNotFoundException::new);
        homeworkAnswer.setStudent(student);
        homeworkAnswer.setHomework(homework);
        return homeworkAnswer;
    }

    private HomeworkAnswerFile mapToHomeworkAnswerFile(HomeworkAnswerFileDto homeworkAnswerFileDto) {
        HomeworkAnswerFile homeworkAnswerFile = homeworkAnswerFileMapper.toHomeworkAnswerFile(homeworkAnswerFileDto);
        String fileContentInBase64 = Base64Helper.getContentFromFileInBase64(homeworkAnswerFileDto.getFileContent());
        byte[] decodedFile = Base64Helper.decodeFileFromBase64(fileContentInBase64);
        homeworkAnswerFile.setFileContent(decodedFile);
        return homeworkAnswerFile;
    }
}
