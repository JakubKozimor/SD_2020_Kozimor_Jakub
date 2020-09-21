package com.learning.service;

import com.learning.domain.dto.MessageDetailsDto;
import com.learning.domain.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface MessageService {
    Page<MessageDto> getReadMessages(Long userId, Pageable pageable);

    void addMessage(MessageDto message) throws IOException;

    Page<MessageDto> getUnreadMessages(Long userId, PageRequest pageable);

    MessageDetailsDto getMessageDetails(Long messageId);

    void updateStatusMessage(Long messageId);
}
