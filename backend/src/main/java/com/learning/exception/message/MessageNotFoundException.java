package com.learning.exception.message;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException() {
        super("Message not found");
    }
}
