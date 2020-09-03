package com.learning.controller;

import com.learning.domain.entity.Subject;
import com.learning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<Set<Subject>> getAllSubjectsByUser(@RequestParam("id") Long id) {
        return new ResponseEntity<>(userService.getAllSubjectsByUserId(id), HttpStatus.OK);
    }
}
