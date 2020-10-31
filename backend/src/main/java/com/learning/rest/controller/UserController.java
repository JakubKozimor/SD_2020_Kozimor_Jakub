package com.learning.rest.controller;

import com.learning.rest.domain.dto.user.UserDto;
import com.learning.rest.domain.dto.user.UsersList;
import com.learning.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<Page<UserDto>> searchUsers(Pageable pageable,
                                                     @RequestParam("search") String search,
                                                     @RequestParam("userId") Long userId) {
        return new ResponseEntity<>(userService.getUsersBySearch(search, pageable, userId), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Void> addUsersToSubject(@RequestBody UsersList usersList,
                                                  @RequestParam("subjectId") Long subjectId) {
        userService.addUsersToSubjects(subjectId, usersList.getStudents());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
