package com.learning.rest.controller;

import com.learning.rest.domain.dto.subject.SubjectDto;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.enums.Week;
import com.learning.rest.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
@CrossOrigin
public class SubjectController {

    private final SubjectService subjectService;

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/all")
    public ResponseEntity<Page<Subject>> getAllSubjectsByUser(@PathVariable Long userId,
                                                              Pageable pageable,
                                                              @RequestParam("week") Week week) {
        return new ResponseEntity<>(subjectService.getAllSubjectsByUserId(userId, pageable, week), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id && hasRole('ROLE_TEACHER')")
    @GetMapping("/{userId}/all-teacher")
    public ResponseEntity<Page<Subject>> getAllSubjectsForTeacher(@PathVariable Long userId,
                                                                  Pageable pageable,
                                                                  @RequestParam("week") Week week) {
        return new ResponseEntity<>(subjectService.getAllSubjectsForTeacher(userId, pageable, week), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id && hasRole('ROLE_TEACHER')")
    @GetMapping("/{userId}/five-first-teacher")
    public ResponseEntity<List<Subject>> getFirstFiveSubjectsForTeacher(@PathVariable Long userId,
                                                                        @RequestParam("week") Week week) {
        return new ResponseEntity<>(subjectService.getFirstFiveSubjectsForTeacher(userId, week), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/five-first")
    public ResponseEntity<List<Subject>> getFirstFiveSubjectsByUser(@PathVariable Long userId,
                                                                    @RequestParam("week") Week week) {
        return new ResponseEntity<>(subjectService.getFirstFiveSubjectsByUserId(userId, week), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping("/new")
    public ResponseEntity<Long> createSubject(@RequestBody SubjectDto subjectDto,
                                              @RequestParam("teacherId") Long teacherId) {
        return new ResponseEntity<>(subjectService.addSubject(teacherId, subjectDto), HttpStatus.CREATED);
    }

    @GetMapping("/subject-details/{subjectId}")
    public ResponseEntity<Subject> getMessageDetails(@PathVariable("subjectId") Long subjectId) {
        return new ResponseEntity<>(subjectService.getSubjectDetails(subjectId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PutMapping("/{subjectId}")
    public ResponseEntity<Void> updateSubject(@PathVariable("subjectId") Long subjectId,
                                              @RequestBody SubjectDto subjectDto) {
        subjectService.updateSubject(subjectDto, subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
