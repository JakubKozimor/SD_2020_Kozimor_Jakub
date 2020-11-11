package com.learning.rest.service.impl;

import com.learning.exception.homework.HomeworkFileNotFoundException;
import com.learning.rest.domain.entity.LiveHomeworkAnswerFile;
import com.learning.rest.domain.repository.LiveHomeworkAnswerFileRepository;
import com.learning.rest.service.LiveHomeworkAnswerFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class LiveHomeworkAnswerFileServiceImpl implements LiveHomeworkAnswerFileService {

    private final LiveHomeworkAnswerFileRepository liveHomeworkAnswerFileRepository;

    @Override
    public ByteArrayInputStream downloadFile(Long fileId, HttpServletResponse response) {
        LiveHomeworkAnswerFile homeworkFile = liveHomeworkAnswerFileRepository.findById(fileId).orElseThrow(HomeworkFileNotFoundException::new);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + homeworkFile.getFileName());
        return new ByteArrayInputStream(homeworkFile.getFileContent());
    }
}
