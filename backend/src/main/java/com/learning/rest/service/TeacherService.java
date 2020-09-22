package com.learning.rest.service;

import com.learning.rest.domain.dto.TeacherDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    Page<TeacherDto> getAllTeachersByUserId(Long id, Pageable pageable);
}
