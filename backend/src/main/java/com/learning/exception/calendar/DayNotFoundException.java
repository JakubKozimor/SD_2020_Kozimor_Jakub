package com.learning.exception.calendar;

public class DayNotFoundException extends RuntimeException {

    public DayNotFoundException() {
        super("Day not found");
    }
}
