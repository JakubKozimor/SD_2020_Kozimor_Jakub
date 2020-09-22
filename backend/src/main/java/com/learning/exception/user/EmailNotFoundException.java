package com.learning.exception.user;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super("Email not found");
    }
}
