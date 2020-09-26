package com.learning.rest.service;

import com.learning.rest.domain.dto.MessageDetailsDto;
import com.learning.rest.domain.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface MessageService {
    Page<MessageDto> getReadMessages(Long userId, Pageable pageable);

    void addMessage(MessageDto message) throws IOException;

    Page<MessageDto> getUnreadMessages(Long userId, Pageable pageable);

    MessageDetailsDto getMessageDetails(Long messageId);

    void updateStatusMessage(Long messageId);
}