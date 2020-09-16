package com.learning.controller;

import com.learning.domain.entity.Subject;
import com.learning.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
@CrossOrigin
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjectsByUser(@RequestParam("id") Long id) {
        return new ResponseEntity<>(subjectService.getAllSubjectsByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/fiveFirst")
    public ResponseEntity<List<Subject>> getFirstFiveSubjectsByUser(@RequestParam("id") Long id) {
        return new ResponseEntity<>(subjectService.getFirstFiveSubjectsByUserId(id), HttpStatus.OK);
    }
}
