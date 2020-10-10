package com.learning.rest.controller;

import com.learning.rest.service.HomeworkAnswerFileService;
import com.learning.rest.service.HomeworkFileService;
import com.learning.rest.service.MessageFileService;
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

    @GetMapping(value = "/homework-answer/download/{fileId}", produces = "application/octet-stream")
    public @ResponseBody
    ResponseEntity<InputStreamResource> downloadHomeworkAnswerFile(@PathVariable Long fileId,
                                                             HttpServletResponse response) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new InputStreamResource(homeworkAnswerFileService.downloadFile(fileId, response)));
    }
}
