package com.learning.controller;

import com.learning.domain.dto.MessageDetailsDto;
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

    @GetMapping("/all-read")
    public ResponseEntity<Page<MessageDto>> getReadMessages(@RequestParam("id") Long id,
                                                            @RequestParam("page") int page,
                                                            @RequestParam("size") int size) {
        return new ResponseEntity<>(messageService.getReadMessages(id, PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/all-unread")
    public ResponseEntity<Page<MessageDto>> getUnreadMessages(@RequestParam("id") Long id,
                                                              @RequestParam("page") int page,
                                                              @RequestParam("size") int size) {
        return new ResponseEntity<>(messageService.getUnreadMessages(id, PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/message-details/{messageId}")
    public ResponseEntity<MessageDetailsDto> getMessageDetails(@PathVariable("messageId") Long messageId) {
        return new ResponseEntity<>(messageService.getMessageDetails(messageId), HttpStatus.OK);
    }

    @PostMapping("/new-message")
    public ResponseEntity<Void> addMessage(@RequestBody MessageDto message) throws IOException {
        messageService.addMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/update-status-message/{messageId}")
    public ResponseEntity<Void> updateStatusMessage(@PathVariable("messageId") Long messageId) {
        messageService.updateStatusMessage(messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
