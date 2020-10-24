package com.learning.chat.controllers;

import com.learning.chat.ChatMessage;
import com.learning.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserService userService;

    @MessageMapping("/send/message/{room}/{userId}")
    public void onReceiveMessage(@DestinationVariable String room,
                                 @DestinationVariable Long userId,
                                 String message) {
        String userFirstNameAndLastName = userService.getUserFirstNameAndLastNameById(userId);
        ChatMessage chatMessage = ChatMessage.builder()
                .userId(userId)
                .date(new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .userName(userFirstNameAndLastName)
                .message(message)
                .build();

        this.simpMessagingTemplate.convertAndSend("/chat/" + room, chatMessage);
//        this.simpMessagingTemplate.convertAndSend("/chat/" + room,
//                new SimpleDateFormat("HH:mm:ss").format(new Date()) + " - " + userFirstNameAndLastName + "\n" + message);

    }

}
