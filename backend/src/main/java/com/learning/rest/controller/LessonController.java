package com.learning.rest.controller;

import com.learning.rest.domain.dto.lesson.LessonDto;
import com.learning.rest.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classes")
@CrossOrigin
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping("/new")
    public ResponseEntity<Void> createHomework(@RequestBody LessonDto classesDto) {
        lessonService.addLesson(classesDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
