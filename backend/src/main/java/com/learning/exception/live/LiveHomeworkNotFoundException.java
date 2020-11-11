package com.learning.exception.live;

public class LiveHomeworkNotFoundException extends RuntimeException {
    public LiveHomeworkNotFoundException() {
        super("Live homework not found");
    }
}
