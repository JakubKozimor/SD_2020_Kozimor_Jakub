package com.learning.exception.homeworkAnswer;

public class HomeworkAnswerNotFoundException extends RuntimeException {
    public HomeworkAnswerNotFoundException() {
        super("Homework answer not found");
    }
}
