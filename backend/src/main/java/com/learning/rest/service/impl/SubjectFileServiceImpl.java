package com.learning.rest.service.impl;

import com.learning.exception.subject.SubjectFileNotFoundException;
import com.learning.rest.domain.entity.SubjectFile;
import com.learning.rest.domain.repository.SubjectFileRepository;
import com.learning.rest.service.SubjectFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class SubjectFileServiceImpl implements SubjectFileService {

    private final SubjectFileRepository subjectFileRepository;

    @Override
    public ByteArrayInputStream downloadFile(Long fileId, HttpServletResponse response) {
        SubjectFile subjectFile = subjectFileRepository.findById(fileId).orElseThrow(SubjectFileNotFoundException::new);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + subjectFile.getFileName());
        return new ByteArrayInputStream(subjectFile.getFileContent());
    }
}
