package com.learning.rest.controller;

import com.learning.rest.domain.dto.lesson.LiveHomeworkDetailsDto;
import com.learning.rest.domain.dto.live.homework.LiveHomeworkDto;
import com.learning.rest.domain.entity.LiveHomework;
import com.learning.rest.service.LiveHomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/live-homeworks")
@CrossOrigin
@RequiredArgsConstructor
public class LiveHomeworkController {

    private final LiveHomeworkService liveHomeworkService;

    @GetMapping("/all")
    public ResponseEntity<List<LiveHomework>> getAllLiveHomeworks(@RequestParam("lessonId") Long lessonId) {
        return new ResponseEntity<>(liveHomeworkService.getAllLiveHomeworks(lessonId), HttpStatus.OK);
    }

    @GetMapping("live-homework-details/{lessonId}")
    public ResponseEntity<LiveHomeworkDetailsDto> getLiveHomeworkDetails(@PathVariable Long lessonId) {
        return new ResponseEntity<>(liveHomeworkService.getLiveHomeworkDetails(lessonId), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping("/add/{lessonId}")
    public ResponseEntity<Void> createLiveHomework(@PathVariable("lessonId") Long lessonId,
                                                   @RequestBody LiveHomeworkDto liveHomework) {
        liveHomeworkService.createLiveHomework(liveHomework, lessonId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PutMapping("/update")
    public ResponseEntity<Void> updateLiveHomework(@RequestBody LiveHomeworkDto liveHomework) {
        liveHomeworkService.updateLiveHomework(liveHomework);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
