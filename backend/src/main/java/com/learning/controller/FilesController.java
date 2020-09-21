package com.learning.controller;

import com.learning.service.MessageFileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/files")
@CrossOrigin
@AllArgsConstructor
public class FilesController {

    private final MessageFileService messageFileService;

    @GetMapping(value = "/message-file/download/{fileId}", produces = "application/octet-stream")
    public @ResponseBody
    ResponseEntity<InputStreamResource> downloadMessageFile(@PathVariable Long fileId,
                                                            HttpServletResponse response) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new InputStreamResource(messageFileService.downloadFile(fileId, response)));
    }
}
