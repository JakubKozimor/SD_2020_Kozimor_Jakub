package com.learning.service.impl;

import com.learning.domain.entity.MessageFile;
import com.learning.domain.repository.MessageFileRepository;
import com.learning.exception.MessageFileNotFoundException;
import com.learning.service.MessageFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class MessageFileServiceImpl implements MessageFileService {

    private final MessageFileRepository messageFileRepository;

    @Override
    public ByteArrayInputStream downloadFile(Long fileId, HttpServletResponse response) {
        MessageFile messageFile = messageFileRepository.findById(fileId).orElseThrow(MessageFileNotFoundException::new);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + messageFile.getFileName());
        return new ByteArrayInputStream(messageFile.getFileContent());
    }
}
