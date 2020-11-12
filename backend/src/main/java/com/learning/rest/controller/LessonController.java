package com.learning.rest.controller;

import com.learning.rest.domain.dto.lesson.LessonDetailsDto;
import com.learning.rest.domain.dto.lesson.LessonDto;
import com.learning.rest.service.LessonService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Long> createLesson(@RequestParam("userId") Long userId,
                                             @RequestBody LessonDto classesDto) {
        return new ResponseEntity<>(lessonService.addLesson(userId, classesDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PutMapping("/update")
    public ResponseEntity<Long> updateLesson(@RequestBody LessonDto classesDto) {
        lessonService.updateLesson(classesDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/lesson-details/{lessonId}")
    public ResponseEntity<LessonDetailsDto> getLessonDetails(@PathVariable("lessonId") Long lessonId) {
        return new ResponseEntity<>(lessonService.geLessonDetails(lessonId), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}")
    public ResponseEntity<List<LessonDetailsDto>> getLivesByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(lessonService.getLiveLesson(userId), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id && hasRole('ROLE_TEACHER')")
    @GetMapping("/teacher/{userId}")
    public ResponseEntity<List<LessonDetailsDto>> getLivesForTeacher(@PathVariable Long userId) {
        return new ResponseEntity<>(lessonService.getLiveLessonForTeacher(userId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PatchMapping("/finish/{classesId}")
    public ResponseEntity<Void> finishLesson(@PathVariable("classesId") Long lessonId) {
        lessonService.finishLive(lessonId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
