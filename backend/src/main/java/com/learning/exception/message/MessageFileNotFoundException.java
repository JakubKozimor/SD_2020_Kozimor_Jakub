package com.learning.exception.message;

public class MessageFileNotFoundException extends RuntimeException {
    public MessageFileNotFoundException() {
        super("File not found");
    }
}
