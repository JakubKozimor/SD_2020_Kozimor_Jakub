package com.learning.exception.lesson;

public class LessonFileNotFoundException extends RuntimeException {
    public LessonFileNotFoundException() {
        super("File not found");
    }
}
