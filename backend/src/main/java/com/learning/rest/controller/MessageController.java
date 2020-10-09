package com.learning.rest.controller;

import com.learning.rest.domain.dto.MessageDetailsDto;
import com.learning.rest.domain.dto.MessageDto;
import com.learning.rest.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
@CrossOrigin
public class MessageController {

    private final MessageService messageService;

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/all-read")
    public ResponseEntity<Page<MessageDto>> getReadMessages(@PathVariable Long userId,
                                                            Pageable pageable) {
        return new ResponseEntity<>(messageService.getReadMessages(userId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("#userId == principal.id")
    @GetMapping("/{userId}/all-unread")
    public ResponseEntity<Page<MessageDto>> getUnreadMessages(@PathVariable Long userId,
                                                              Pageable pageable) {
        return new ResponseEntity<>(messageService.getUnreadMessages(userId, pageable), HttpStatus.OK);
    }

    @GetMapping("/message-details/{messageId}")
    public ResponseEntity<MessageDetailsDto> getMessageDetails(@PathVariable("messageId") Long messageId) {
        return new ResponseEntity<>(messageService.getMessageDetails(messageId), HttpStatus.OK);
    }

    @PostMapping("/new-message")
    public ResponseEntity<Void> addMessage(@RequestBody MessageDto message) throws IOException {
        messageService.addMessage(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/update-status-message/{messageId}")
    public ResponseEntity<Void> updateStatusMessage(@PathVariable("messageId") Long messageId) {
        messageService.updateStatusMessage(messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
