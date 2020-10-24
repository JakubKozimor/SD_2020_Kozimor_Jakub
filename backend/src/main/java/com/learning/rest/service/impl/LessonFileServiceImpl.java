package com.learning.rest.service.impl;

import com.learning.exception.lesson.LessonFileNotFoundException;
import com.learning.rest.domain.entity.LessonFile;
import com.learning.rest.domain.repository.LessonFileRepository;
import com.learning.rest.service.LessonFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class LessonFileServiceImpl implements LessonFileService {

    private final LessonFileRepository lessonFileRepository;

    @Override
    public ByteArrayInputStream downloadFile(Long fileId, HttpServletResponse response) {
        LessonFile lessonFile = lessonFileRepository.findById(fileId).orElseThrow(LessonFileNotFoundException::new);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + lessonFile.getFileName());
        return new ByteArrayInputStream(lessonFile.getFileContent());
    }
}
