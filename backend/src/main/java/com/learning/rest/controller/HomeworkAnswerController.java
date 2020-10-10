package com.learning.rest.controller;

import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDetailsDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDto;
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

    @GetMapping("/homework-answer-details/{homeworkId}")
    public ResponseEntity<HomeworkAnswerDetailsDto> getHomeworkAnswerDetails(@PathVariable Long homeworkId,
                                                                             @RequestParam("userId") Long userId) {
        return new ResponseEntity<>(homeworkAnswerService.getHomeworkAnswerDetails(homeworkId, userId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addHomeworkAnswer(@RequestBody HomeworkAnswerDto homework) {
        homeworkAnswerService.addHomeworkAnswer(homework);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateHomeworkAnswer(@RequestBody HomeworkAnswerDto homework) {
        homeworkAnswerService.updateHomeworkAnswer(homework);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
