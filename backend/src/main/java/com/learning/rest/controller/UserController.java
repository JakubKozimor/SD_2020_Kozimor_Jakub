package com.learning.rest.controller;

import com.learning.rest.domain.entity.User;
import com.learning.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getAllSubjectsByUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getTeacherById(id), HttpStatus.OK);
    }
}
