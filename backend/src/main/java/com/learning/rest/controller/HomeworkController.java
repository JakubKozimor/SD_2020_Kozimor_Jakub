package com.learning.rest.controller;

import com.learning.rest.domain.entity.Homework;
import com.learning.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homeworks")
@CrossOrigin
@RequiredArgsConstructor
public class HomeworkController {

    private final UserService userService;

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/all")
    public ResponseEntity<Page<Homework>> getAllHomeworkByUser(@PathVariable Long userId,
                                                               Pageable pageable) {
        return new ResponseEntity<>(userService.getAllHomeworks(userId, pageable), HttpStatus.OK);
    }
}
