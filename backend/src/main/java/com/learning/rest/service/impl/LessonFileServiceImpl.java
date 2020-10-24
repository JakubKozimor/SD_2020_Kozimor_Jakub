package com.learning.rest.service.impl;

import com.learning.exception.homework.HomeworkFileNotFoundException;
import com.learning.rest.domain.entity.HomeworkFile;
import com.learning.rest.domain.repository.HomeworkFileRepository;
import com.learning.rest.service.HomeworkFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class HomeworkFileServiceImpl implements HomeworkFileService {

    private final HomeworkFileRepository homeworkFileRepository;

    @Override
    public ByteArrayInputStream downloadFile(Long fileId, HttpServletResponse response) {
        HomeworkFile homeworkFile = homeworkFileRepository.findById(fileId).orElseThrow(HomeworkFileNotFoundException::new);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + homeworkFile.getFileName());
        return new ByteArrayInputStream(homeworkFile.getFileContent());
    }
}
