package com.learning.exception.user;

public class EmailAlreadyExistException extends RuntimeException {

    public EmailAlreadyExistException() {
        super("Email already exist");
    }
}
