package com.learning.rest.controller;

import com.learning.rest.domain.dto.homework.HomeworkDetailsDto;
import com.learning.rest.domain.dto.homework.HomeworkDto;
import com.learning.rest.domain.dto.homework.RatedHomeworkDto;
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

    @PreAuthorize("#teacherId == principal.id && hasRole('ROLE_TEACHER')")
    @GetMapping("/not-rated/{teacherId}")
    public ResponseEntity<Page<Homework>> getAllNotRatedHomeworks(@PathVariable Long teacherId,
                                                                  Pageable pageable) {
        return new ResponseEntity<>(homeworkService.getNotRatedHomeworksForTeacher(teacherId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("#teacherId == principal.id && hasRole('ROLE_TEACHER')")
    @GetMapping("/rated/{teacherId}")
    public ResponseEntity<Page<Homework>> getAllRatedHomeworks(@PathVariable Long teacherId,
                                                               Pageable pageable) {
        return new ResponseEntity<>(homeworkService.getRatedHomeworksForTeacher(teacherId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("#teacherId == principal.id && hasRole('ROLE_TEACHER')")
    @GetMapping("/active/{teacherId}")
    public ResponseEntity<Page<Homework>> getAllActiveHomeworks(@PathVariable Long teacherId,
                                                                Pageable pageable) {
        return new ResponseEntity<>(homeworkService.getAllActiveForTeacher(teacherId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/active")
    public ResponseEntity<Page<Homework>> getAllActiveHomeworkByUser(@PathVariable Long userId,
                                                                     Pageable pageable,
                                                                     @RequestParam("subjectId") Long subjectId) {
        return new ResponseEntity<>(homeworkService.getAllActiveHomeworks(userId, pageable, subjectId), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/late")
    public ResponseEntity<Page<Homework>> getAllLateHomeworkByUser(@PathVariable Long userId,
                                                                   Pageable pageable,
                                                                   @RequestParam("subjectId") Long subjectId) {
        return new ResponseEntity<>(homeworkService.getAllLateHomeworks(userId, pageable, subjectId), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/done")
    public ResponseEntity<Page<Homework>> getAllDoneHomeworkByUser(@PathVariable Long userId,
                                                                   Pageable pageable,
                                                                   @RequestParam("subjectId") Long subjectId) {
        return new ResponseEntity<>(homeworkService.getAllDoneHomeworks(userId, pageable, subjectId), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/rated")
    public ResponseEntity<Page<RatedHomeworkDto>> getAllRatedHomeworkByUser(@PathVariable Long userId,
                                                                            Pageable pageable,
                                                                            @RequestParam("subjectId") Long subjectId) {
        return new ResponseEntity<>(homeworkService.getAllRatedHomeworks(userId, pageable, subjectId), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/five-first")
    public ResponseEntity<List<Homework>> getFiveActiveHomeworkByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(homeworkService.getFiveActiveHomeworks(userId), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id && hasRole('ROLE_TEACHER')")
    @GetMapping("/{userId}/five-first-teacher")
    public ResponseEntity<List<Homework>> getFiveActiveHomeworkForTeacher(@PathVariable Long userId) {
        return new ResponseEntity<>(homeworkService.getFiveActiveHomeworksForTeacher(userId), HttpStatus.OK);
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

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PutMapping("/{homeworkId}")
    public ResponseEntity<Void> updateHomework(@PathVariable("homeworkId") Long homeworkId,
                                               @RequestBody HomeworkDto homework) {
        homeworkService.updateHomework(homework, homeworkId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
