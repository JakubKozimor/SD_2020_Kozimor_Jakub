package com.learning.controller;

import com.learning.domain.entity.Subject;
import com.learning.service.SubjectService;
import com.learning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
@CrossOrigin
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Subject>> getAllSubjectsByUser(@RequestParam("id") Long id) {
        return new ResponseEntity<>(subjectService.getAllSubjectsByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/getFiveFirst")
    public ResponseEntity<List<Subject>> getFirstFiveSubjectsByUser(@RequestParam("id") Long id) {
        return new ResponseEntity<>(subjectService.getFirstFiveSubjectsByUserId(id), HttpStatus.OK);
    }
}
