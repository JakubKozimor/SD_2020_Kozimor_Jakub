package com.learning.rest.service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

public interface LessonFileService {
    ByteArrayInputStream downloadFile(Long fileId, HttpServletResponse response);
}
