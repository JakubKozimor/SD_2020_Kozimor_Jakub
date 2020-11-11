package com.learning.rest.controller;

import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerDetailsDto;
import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerDto;
import com.learning.rest.service.LiveHomeworkAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/live-homework-answer")
@CrossOrigin
@RequiredArgsConstructor
public class LiveHomeworkAnswerController {

    private final LiveHomeworkAnswerService liveHomeworkAnswerService;

    @GetMapping("all/{liveHomeworkId}")
    public ResponseEntity<List<LiveHomeworkAnswerDetailsDto>> getAllAnswersForTeacher(@PathVariable Long liveHomeworkId) {
        return new ResponseEntity<>(liveHomeworkAnswerService.getAllAnswersByLiveHomeworkId(liveHomeworkId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addLiveHomeworkAnswer(@RequestBody LiveHomeworkAnswerDto liveHomeworkAnswerDto) {
        liveHomeworkAnswerService.addLiveHomeworkAnswer(liveHomeworkAnswerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/live-homework-answer-details/{liveHomeworkId}")
    public ResponseEntity<LiveHomeworkAnswerDto> getHomeworkAnswerDetails(@PathVariable Long liveHomeworkId,
                                                                          @RequestParam("userId") Long userId) {
        return new ResponseEntity<>(liveHomeworkAnswerService.getLiveHomeworkAnswerDetails(liveHomeworkId, userId), HttpStatus.OK);
    }
}
