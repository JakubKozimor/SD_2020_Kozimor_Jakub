package com.learning.controller;

import com.learning.domain.dto.TeacherDto;
import com.learning.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
@CrossOrigin
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/all")
    public ResponseEntity<Page<TeacherDto>> getTeachersByUser(@RequestParam("id") Long id,
                                                              @RequestParam("page") int page,
                                                              @RequestParam("size") int size) {
        return new ResponseEntity<>(teacherService.getAllTeachersByUserId(id, PageRequest.of(page, size)), HttpStatus.OK);
    }
}
