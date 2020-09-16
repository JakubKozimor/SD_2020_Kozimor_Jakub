package com.learning.controller;

import com.learning.domain.dto.MessageDto;
import com.learning.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
@CrossOrigin
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/allRead")
    public ResponseEntity<Page<MessageDto>> getReadMessages(@RequestParam("id") Long id,
                                                            @RequestParam("page") int page,
                                                            @RequestParam("size") int size) {
        return new ResponseEntity<>(messageService.getReadMessages(id, PageRequest.of(page, size)), HttpStatus.OK);
    }


    @PostMapping("/newMessage")
    public ResponseEntity<Void> addMessage(@RequestBody MessageDto message) throws IOException {
        messageService.addMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
