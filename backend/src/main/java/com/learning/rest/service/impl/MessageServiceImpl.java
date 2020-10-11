package com.learning.rest.service.impl;

import com.learning.exception.message.MessageNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.base64.Base64Helper;
import com.learning.rest.domain.dto.message.MessageDetailsDto;
import com.learning.rest.domain.dto.message.MessageDto;
import com.learning.rest.domain.dto.message.MessageFileDto;
import com.learning.rest.domain.entity.Message;
import com.learning.rest.domain.entity.MessageFile;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.entity.enums.MessageStatus;
import com.learning.rest.domain.mapper.MessageFileMapper;
import com.learning.rest.domain.mapper.MessageMapper;
import com.learning.rest.domain.repository.MessageRepository;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.rest.pageable.PageHelper;
import com.learning.rest.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
        return (Page<MessageDto>) PageHelper.preparePageFromList(messageDtoListToUser, pageable);
    }

    @Override
    public Page<MessageDto> getUnreadMessages(Long userId, Pageable pageable) {
        User userTo = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<MessageDto> messageDtoListToUser = messageRepository.findAllByUserToAndStatus(userTo, MessageStatus.UNREAD)
                .stream()
                .map(messageMapper::toMessageDto)
                .collect(Collectors.toList());
        return (Page<MessageDto>) PageHelper.preparePageFromList(messageDtoListToUser, pageable);
    }

    @Override
    public Page<MessageDto> getSendMessages(Long userId, Pageable pageable) {
        User userFrom = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Message> allMessagesFromUser = messageRepository.findAllByUserFrom(userFrom);
        List<MessageDto> allMessagesFromUserDto = allMessagesFromUser
                .stream()
                .map(messageMapper::toMessageDto)
                .collect(Collectors.toList());
        return (Page<MessageDto>) PageHelper.preparePageFromList(allMessagesFromUserDto, pageable);
    }

    @Override
    public MessageDetailsDto getMessageDetails(Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(MessageNotFoundException::new);
        return messageMapper.toMessageDetailsDto(message);
    }

    @Override
    @Transactional
    public void addMessage(MessageDto messageDto) {
        User userFrom = userRepository.findById(messageDto.getUserFrom()).orElseThrow(UserNotFoundException::new);
        User userTo = userRepository.findById(messageDto.getUserTo()).orElseThrow(UserNotFoundException::new);
        Message message = messageMapper.toMessage(messageDto);
        List<MessageFileDto> messageFilesDto = messageDto.getFiles();
        if (messageFilesDto != null) {
            messageDto.getFiles()
                    .stream()
                    .map(this::mapToMessageFile)
                    .forEach(message::addFile);
        }
        message.setUserFrom(userFrom);
        message.setUserTo(userTo);
        message.setStatus(MessageStatus.UNREAD);
        message.setDate(LocalDateTime.now());
        messageRepository.save(message);
    }

    private MessageFile mapToMessageFile(MessageFileDto messageFileDto) {
        MessageFile messageFile = messageFileMapper.toMessageFile(messageFileDto);
        String fileContentInBase64 = Base64Helper.getContentFromFileInBase64(messageFileDto.getFileContent());
        byte[] decodedFile = Base64Helper.decodeFileFromBase64(fileContentInBase64);
        messageFile.setFileContent(decodedFile);
        return messageFile;
    }

    @Override
    public void updateStatusMessage(Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(MessageNotFoundException::new);
        message.setStatus(MessageStatus.READ);
        messageRepository.save(message);
    }
}
