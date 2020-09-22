package com.learning.rest.controller;

import com.learning.rest.domain.entity.Subject;
import com.learning.rest.service.SubjectService;
import lombok.RequiredArgsConstructor;
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

    @PreAuthorize("#id == principal.id")
    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjectsByUser(@RequestParam("id") Long id) {
        return new ResponseEntity<>(subjectService.getAllSubjectsByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/fiveFirst")
    public ResponseEntity<List<Subject>> getFirstFiveSubjectsByUser(@RequestParam("id") Long id) {
        return new ResponseEntity<>(subjectService.getFirstFiveSubjectsByUserId(id), HttpStatus.OK);
    }
}
