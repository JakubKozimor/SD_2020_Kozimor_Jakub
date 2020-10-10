package com.learning.rest.controller;

import com.learning.rest.domain.dto.teacher.TeacherDto;
import com.learning.rest.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
@CrossOrigin
public class TeacherController {

    private final TeacherService teacherService;

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/all")
    public ResponseEntity<Page<TeacherDto>> getTeachersByUser(@PathVariable Long userId,
                                                              Pageable pageable) {
        return new ResponseEntity<>(teacherService.getAllTeachersByUserId(userId, pageable), HttpStatus.OK);
    }
}
