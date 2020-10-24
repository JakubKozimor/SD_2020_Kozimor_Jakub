package com.learning.rest.service.map;

import com.learning.rest.base64.Base64Helper;
import com.learning.rest.domain.dto.homework.HomeworkFileDto;
import com.learning.rest.domain.dto.homework.RatedHomeworkDto;
import com.learning.rest.domain.dto.lesson.LessonFileDto;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.HomeworkAnswer;
import com.learning.rest.domain.entity.HomeworkFile;
import com.learning.rest.domain.entity.LessonFile;
import com.learning.rest.domain.mapper.HomeworkFileMapper;
import com.learning.rest.domain.mapper.HomeworkMapper;
import com.learning.rest.domain.mapper.LessonFileMapper;
import com.learning.rest.domain.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LessonMap {

    private final LessonFileMapper lessonFileMapper;

    public LessonFile mapToLessonFile(LessonFileDto lessonFileDto) {
        LessonFile lessonFile = lessonFileMapper.toLessonFile(lessonFileDto);
        String fileContentInBase64 = Base64Helper.getContentFromFileInBase64(lessonFileDto.getFileContent());
        byte[] decodedFile = Base64Helper.decodeFileFromBase64(fileContentInBase64);
        lessonFile.setFileContent(decodedFile);
        return lessonFile;
    }
}
