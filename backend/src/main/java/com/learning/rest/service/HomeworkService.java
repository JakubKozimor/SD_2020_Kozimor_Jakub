package com.learning.rest.service;

import com.learning.rest.domain.dto.HomeworkDetailsDto;
import com.learning.rest.domain.dto.HomeworkDto;
import com.learning.rest.domain.entity.Homework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HomeworkService {
    Page<Homework> getAllActiveHomeworks(Long teacherId, Pageable pageable);

    List<Homework> getFiveActiveHomework(Long userId);

    HomeworkDetailsDto getHomeworkDetails(Long homeworkId);

    void createHomework(HomeworkDto homeworkDto, Long subjectId);
}
