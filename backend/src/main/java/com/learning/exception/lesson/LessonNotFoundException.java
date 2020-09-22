package com.learning.exception.lesson;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException() {
        super("Lesson not found");
    }
}
