package com.learning.service;

import com.learning.domain.dto.TeacherDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    Page<TeacherDto> getAllTeachersByUserId(Long id, Pageable pageable);
}
