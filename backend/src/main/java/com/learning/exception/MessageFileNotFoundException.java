package com.learning.exception;

public class MessageFileNotFoundException extends RuntimeException {
    public MessageFileNotFoundException() {
        super("File not found");
    }
}
