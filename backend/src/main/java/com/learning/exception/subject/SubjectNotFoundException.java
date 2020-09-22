package com.learning.exception.subject;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException() {
        super("Subject not found");
    }
}
