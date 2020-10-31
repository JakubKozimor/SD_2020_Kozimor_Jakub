package com.learning.exception.school;

public class SchoolNotFoundException extends RuntimeException {
    public SchoolNotFoundException() {
        super("School not found");
    }
}
