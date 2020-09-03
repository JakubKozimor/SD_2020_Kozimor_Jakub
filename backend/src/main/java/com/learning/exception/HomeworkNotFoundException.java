package com.learning.exception;

public class HomeworkNotFoundException extends RuntimeException {
    public HomeworkNotFoundException() {
        super("Homework not found");
    }
}
