package com.learning.exception.subject;

public class SubjectFileNotFoundException extends RuntimeException {
    public SubjectFileNotFoundException() {
        super("File not found");
    }
}
