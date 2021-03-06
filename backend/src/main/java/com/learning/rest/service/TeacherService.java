package com.learning.rest.service;

import com.learning.rest.domain.dto.teacher.TeacherDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    Page<TeacherDto> getAllTeachersByUserId(Long userId, Pageable pageable);
}
