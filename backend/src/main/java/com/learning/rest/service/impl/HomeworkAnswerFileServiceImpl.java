package com.learning.rest.service.impl;

import com.learning.exception.homework.HomeworkFileNotFoundException;
import com.learning.rest.domain.entity.HomeworkAnswerFile;
import com.learning.rest.domain.repository.HomeworkAnswerFileRepository;
import com.learning.rest.service.HomeworkAnswerFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class HomeworkAnswerFileServiceImpl implements HomeworkAnswerFileService {

    private final HomeworkAnswerFileRepository homeworkAnswerFileRepository;

    @Override
    public ByteArrayInputStream downloadFile(Long fileId, HttpServletResponse response) {
        HomeworkAnswerFile homeworkAnswerFile = homeworkAnswerFileRepository.findById(fileId).orElseThrow(HomeworkFileNotFoundException::new);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + homeworkAnswerFile.getFileName());
        return new ByteArrayInputStream(homeworkAnswerFile.getFileContent());
    }
}
