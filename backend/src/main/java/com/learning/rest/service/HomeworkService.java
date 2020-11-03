package com.learning.rest.service;

import com.learning.rest.domain.dto.homework.HomeworkDetailsDto;
import com.learning.rest.domain.dto.homework.HomeworkDto;
import com.learning.rest.domain.dto.homework.RatedHomeworkDto;
import com.learning.rest.domain.entity.Homework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HomeworkService {
    Page<Homework> getAllActiveHomeworks(Long teacherId, Pageable pageable, Long subjectId);

    Page<Homework> getAllLateHomeworks(Long userId, Pageable pageable, Long subjectId);

    Page<Homework> getAllDoneHomeworks(Long userId, Pageable pageable, Long subjectId);

    Page<RatedHomeworkDto> getAllRatedHomeworks(Long userId, Pageable pageable, Long subjectId);

    List<Homework> getFiveActiveHomeworks(Long userId);

    List<Homework> getFiveActiveHomeworksForTeacher(Long userId);

    Page<Homework> getNotRatedHomeworksForTeacher(Long teacherId, Pageable pageable);

    Page<Homework> getRatedHomeworksForTeacher(Long teacherId, Pageable pageable);

    Page<Homework> getAllActiveForTeacher(Long teacherId, Pageable pageable);

    HomeworkDetailsDto getHomeworkDetails(Long homeworkId);

    void createHomework(HomeworkDto homeworkDto, Long subjectId);
}
