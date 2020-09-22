package com.learning.exception.homework;

public class HomeworkNotFoundException extends RuntimeException {
    public HomeworkNotFoundException() {
        super("Homework not found");
    }
}
