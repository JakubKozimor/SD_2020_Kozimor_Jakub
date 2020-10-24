package com.learning.chat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessage {
    private Long userId;
    private String userName;
    private String date;
    private String message;
}
