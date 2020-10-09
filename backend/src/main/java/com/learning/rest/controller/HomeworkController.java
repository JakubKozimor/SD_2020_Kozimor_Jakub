package com.learning.rest.controller;

import com.learning.rest.domain.dto.HomeworkDetailsDto;
import com.learning.rest.domain.dto.HomeworkDto;
import com.learning.rest.domain.dto.RatedHomeworkDto;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.service.HomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/homeworks")
@CrossOrigin
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkService homeworkService;

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/active")
    public ResponseEntity<Page<Homework>> getAllActiveHomeworkByUser(@PathVariable Long userId,
                                                                     Pageable pageable) {
        return new ResponseEntity<>(homeworkService.getAllActiveHomeworks(userId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/late")
    public ResponseEntity<Page<Homework>> getAllLateHomeworkByUser(@PathVariable Long userId,
                                                                     Pageable pageable) {
        return new ResponseEntity<>(homeworkService.getAllLateHomeworks(userId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/done")
    public ResponseEntity<Page<Homework>> getAllDoneHomeworkByUser(@PathVariable Long userId,
                                                                   Pageable pageable) {
        return new ResponseEntity<>(homeworkService.getAllDoneHomeworks(userId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/rated")
    public ResponseEntity<Page<RatedHomeworkDto>> getAllRatedHomeworkByUser(@PathVariable Long userId,
                                                                            Pageable pageable) {
        return new ResponseEntity<>(homeworkService.getAllRatedHomeworks(userId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/five-first")
    public ResponseEntity<List<Homework>> getFiveActiveHomeworkByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(homeworkService.getFiveActiveHomeworks(userId), HttpStatus.OK);
    }


    @GetMapping("/homework-details/{homeworkId}")
    public ResponseEntity<HomeworkDetailsDto> getMessageDetails(@PathVariable("homeworkId") Long homeworkId) {
        return new ResponseEntity<>(homeworkService.getHomeworkDetails(homeworkId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping("/{subjectId}/add")
    public ResponseEntity<Void> createHomework(@PathVariable("subjectId") Long subjectId,
                                               @RequestBody HomeworkDto homework) {
        homeworkService.createHomework(homework, subjectId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
