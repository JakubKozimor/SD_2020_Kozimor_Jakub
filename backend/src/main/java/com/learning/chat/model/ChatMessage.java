package com.learning.chat.model;


import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private String value;
    private String user;
    private String userColor;
}
