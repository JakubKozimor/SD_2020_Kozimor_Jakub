package com.learning.rest.controller;

import com.learning.rest.domain.dto.HomeworkAnswerDto;
import com.learning.rest.service.HomeworkAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homework-answer")
@CrossOrigin
@RequiredArgsConstructor
public class HomeworkAnswerController {

    private final HomeworkAnswerService homeworkAnswerService;

    @PostMapping("/add")
    public ResponseEntity<Void> addHomeworkAnswer(@RequestBody HomeworkAnswerDto homework) {
        homeworkAnswerService.addHomeworkAnswer(homework);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
