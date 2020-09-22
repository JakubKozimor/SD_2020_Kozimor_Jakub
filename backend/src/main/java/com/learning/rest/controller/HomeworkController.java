package com.learning.rest.controller;

import com.learning.rest.domain.entity.Homework;
import com.learning.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homeworks")
@CrossOrigin
@RequiredArgsConstructor
public class HomeworkController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<Page<Homework>> getAllHomeworkByUser(@RequestParam("id") Long id,
                                                               @RequestParam("page") int page,
                                                               @RequestParam("size") int size) {
        return new ResponseEntity<>(userService.getAllHomeworks(id, PageRequest.of(page, size)), HttpStatus.OK);
    }
}
