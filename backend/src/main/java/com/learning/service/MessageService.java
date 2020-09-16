package com.learning.service;

import com.learning.domain.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface MessageService {
    Page<MessageDto> getReadMessages(Long userId, Pageable pageable);

    void addMessage(MessageDto message) throws IOException;
}
