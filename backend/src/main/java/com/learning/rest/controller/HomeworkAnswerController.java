package com.learning.rest.controller;

import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDetailsDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerUserDetailsDto;
import com.learning.rest.service.HomeworkAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/homework-answer-details")
    public ResponseEntity<HomeworkAnswerDetailsDto> getHomeworkAnswerDetailsByAnswerId(@RequestParam("answerId") Long answerId) {
        return new ResponseEntity<>(homeworkAnswerService.getHomeworkAnswerDetailsByAnswerId(answerId), HttpStatus.OK);
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

    @GetMapping("/all-no-grade")
    public ResponseEntity<Page<HomeworkAnswerUserDetailsDto>> getAllHomeworkAnswerWithNoGrade(@RequestParam("homeworkId") Long homeworkId,
                                                                                              Pageable pageable) {
        return new ResponseEntity<>(homeworkAnswerService.getAllHomeworkAnswersWithNoGrade(homeworkId, pageable), HttpStatus.OK);
    }

    @GetMapping("/all-grade")
    public ResponseEntity<Page<HomeworkAnswerUserDetailsDto>> getAllHomeworkAnswerWithGrade(@RequestParam("homeworkId") Long homeworkId,
                                                                                            Pageable pageable) {
        return new ResponseEntity<>(homeworkAnswerService.getAllHomeworkAnswersWithGrade(homeworkId, pageable), HttpStatus.OK);
    }

    @PatchMapping("/grade/{homeworkAnswerId}")
    public ResponseEntity<Void> updateStatusMessage(@PathVariable Long homeworkAnswerId,
                                                    @RequestParam("grade") String grade,
                                                    @RequestParam("comment") String comment) {
        homeworkAnswerService.addGrade(homeworkAnswerId, grade, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
