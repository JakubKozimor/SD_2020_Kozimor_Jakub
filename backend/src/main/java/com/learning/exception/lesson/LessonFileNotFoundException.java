package com.learning.exception.homework;

public class HomeworkFileNotFoundException extends RuntimeException {
    public HomeworkFileNotFoundException() {
        super("File not found");
    }
}
