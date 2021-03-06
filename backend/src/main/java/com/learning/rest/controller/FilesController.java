package com.learning.rest.controller;

import com.learning.rest.service.*;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/files")
@CrossOrigin
@AllArgsConstructor
public class FilesController {

    private final MessageFileService messageFileService;
    private final HomeworkFileService homeworkFileService;
    private final HomeworkAnswerFileService homeworkAnswerFileService;
    private final SubjectFileService subjectFileService;
    private final LessonFileService lessonFileService;
    private final LiveHomeworkFileService liveHomeworkFileService;
    private final LiveHomeworkAnswerFileService liveHomeworkAnswerFileService;

    @GetMapping(value = "/message-file/download/{fileId}", produces = "application/octet-stream")
    public @ResponseBody
    ResponseEntity<InputStreamResource> downloadMessageFile(@PathVariable Long fileId,
                                                            HttpServletResponse response) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new InputStreamResource(messageFileService.downloadFile(fileId, response)));
    }

    @GetMapping(value = "/homework-file/download/{fileId}", produces = "application/octet-stream")
    public @ResponseBody
    ResponseEntity<InputStreamResource> downloadHomeworkFile(@PathVariable Long fileId,
                                                             HttpServletResponse response) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new InputStreamResource(homeworkFileService.downloadFile(fileId, response)));
    }

    @GetMapping(value = "/live-homework/download/{fileId}", produces = "application/octet-stream")
    public @ResponseBody
    ResponseEntity<InputStreamResource> downloadLiveHomeworkFile(@PathVariable Long fileId,
                                                                 HttpServletResponse response) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new InputStreamResource(liveHomeworkFileService.downloadFile(fileId, response)));
    }

    @GetMapping(value = "/live-homework-answer/download/{fileId}", produces = "application/octet-stream")
    public @ResponseBody
    ResponseEntity<InputStreamResource> downloadLiveHomeworkAnswerFile(@PathVariable Long fileId,
                                                                       HttpServletResponse response) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new InputStreamResource(liveHomeworkAnswerFileService.downloadFile(fileId, response)));
    }

    @GetMapping(value = "/homework-answer/download/{fileId}", produces = "application/octet-stream")
    public @ResponseBody
    ResponseEntity<InputStreamResource> downloadHomeworkAnswerFile(@PathVariable Long fileId,
                                                                   HttpServletResponse response) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new InputStreamResource(homeworkAnswerFileService.downloadFile(fileId, response)));
    }

    @GetMapping(value = "/subject/download/{fileId}", produces = "application/octet-stream")
    public @ResponseBody
    ResponseEntity<InputStreamResource> downloadSubjectFile(@PathVariable Long fileId,
                                                            HttpServletResponse response) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new InputStreamResource(subjectFileService.downloadFile(fileId, response)));
    }

    @GetMapping(value = "/lesson/download/{fileId}", produces = "application/octet-stream")
    public @ResponseBody
    ResponseEntity<InputStreamResource> downloadLessonFile(@PathVariable Long fileId,
                                                           HttpServletResponse response) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new InputStreamResource(lessonFileService.downloadFile(fileId, response)));
    }
}
