package com.learning.rest.service.impl;

import com.learning.exception.homework.HomeworkFileNotFoundException;
import com.learning.rest.domain.entity.HomeworkFile;
import com.learning.rest.domain.entity.LiveHomeworkFile;
import com.learning.rest.domain.repository.LiveHomeworkFileRepository;
import com.learning.rest.service.LiveHomeworkFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class LiveHomeworkFileServiceImpl implements LiveHomeworkFileService {

    private final LiveHomeworkFileRepository liveHomeworkFileRepository;

    @Override
    public ByteArrayInputStream downloadFile(Long fileId, HttpServletResponse response) {
        LiveHomeworkFile homeworkFile = liveHomeworkFileRepository.findById(fileId).orElseThrow(HomeworkFileNotFoundException::new);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + homeworkFile.getFileName());
        return new ByteArrayInputStream(homeworkFile.getFileContent());
    }
}
