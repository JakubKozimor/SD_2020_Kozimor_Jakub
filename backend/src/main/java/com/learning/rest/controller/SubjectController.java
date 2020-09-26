package com.learning.rest.controller;

import com.learning.rest.domain.entity.Subject;
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
                                                              Pageable pageable) {
        return new ResponseEntity<>(subjectService.getAllSubjectsByUserId(userId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/fiveFirst")
    public ResponseEntity<List<Subject>> getFirstFiveSubjectsByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(subjectService.getFirstFiveSubjectsByUserId(userId), HttpStatus.OK);
    }
}