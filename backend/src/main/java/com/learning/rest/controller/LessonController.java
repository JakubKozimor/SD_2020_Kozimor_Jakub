package com.learning.rest.controller;

import com.learning.rest.domain.dto.lesson.LessonDetailsDto;
import com.learning.rest.domain.dto.lesson.LessonDto;
import com.learning.rest.domain.entity.Lesson;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.enums.Week;
import com.learning.rest.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@CrossOrigin
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PreAuthorize("#userId == principal.id && hasRole('ROLE_TEACHER')")
    @PostMapping("/new")
    public ResponseEntity<Long> createHomework(@RequestParam("userId") Long userId,
                                               @RequestBody LessonDto classesDto) {
        return new ResponseEntity<>(lessonService.addLesson(userId, classesDto),HttpStatus.CREATED);
    }

    @GetMapping("/lesson-details/{lessonId}")
    public ResponseEntity<LessonDetailsDto> getMessageDetails(@PathVariable("lessonId") Long lessonId) {
        return new ResponseEntity<>(lessonService.geLessonDetails(lessonId), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}")
    public ResponseEntity<List<LessonDetailsDto>> getAllSubjectsByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(lessonService.getLiveLesson(userId), HttpStatus.OK);
    }
}
