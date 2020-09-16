package com.learning.service.impl;

import com.learning.constants.Constants;
import com.learning.domain.dto.MessageDto;
import com.learning.domain.dto.MessageFileDto;
import com.learning.domain.entity.Message;
import com.learning.domain.entity.MessageFile;
import com.learning.domain.entity.User;
import com.learning.domain.entity.enums.MessageStatus;
import com.learning.domain.mapper.MessageFileMapper;
import com.learning.domain.mapper.MessageMapper;
import com.learning.domain.repository.MessageRepository;
import com.learning.domain.repository.UserRepository;
import com.learning.exception.UserNotFoundException;
import com.learning.pageable.PageHelper;
import com.learning.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;
    private final MessageFileMapper messageFileMapper;


    @Override
    public Page<MessageDto> getReadMessages(Long userId, Pageable pageable) {
        User userTo = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<MessageDto> messageDtoListToUser = messageRepository.findAllByUserToAndStatus(userTo, MessageStatus.READ)
                .stream()
                .map(messageMapper::toMessageDto)
                .collect(Collectors.toList());
        return (Page<MessageDto>) PageHelper.preparePageFromList(messageDtoListToUser,pageable);
    }

    @Override
    @Transactional
    public void addMessage(MessageDto messageDto) {
        User userFrom = userRepository.findById(messageDto.getUserFrom()).orElseThrow(UserNotFoundException::new);
        User userTo = userRepository.findById(messageDto.getUserTo()).orElseThrow(UserNotFoundException::new);
        Message message = messageMapper.toMessage(messageDto);
        messageDto.getFiles().stream()
                .map(this::mapToMessageFile)
                .forEach(message::addFile);
        message.setUserFrom(userFrom);
        message.setUserTo(userTo);
        message.setStatus(MessageStatus.UNREAD);
        messageRepository.save(message);
    }

    private String getContentFromFileInBase64(String fileInBase64) {
        return fileInBase64.split(",")[Constants.POSITION_OF_CONTENT_IN_BASE_64];
    }

    private MessageFile mapToMessageFile(MessageFileDto messageFileDto) {
        MessageFile messageFile = messageFileMapper.toMessageFile(messageFileDto);
        messageFile.setFileContent(this.getContentFromFileInBase64(messageFileDto.getFileContent()).getBytes());
        return messageFile;
    }
}
