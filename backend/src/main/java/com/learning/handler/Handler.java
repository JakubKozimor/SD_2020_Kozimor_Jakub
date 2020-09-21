package com.learning.handler;

import com.learning.domain.dto.ApiError;
import com.learning.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class Handler {

    @ExceptionHandler(value = {UserNotFoundException.class, SubjectNotFoundException.class, LessonNotFoundException.class, HomeworkNotFoundException.class, MessageNotFoundException.class, MessageFileNotFoundException.class})
    public ResponseEntity<ApiError> handleNotFound(RuntimeException exception) {
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError("NOT_FOUND", exception.getMessage(), httpStatus.value());
        return new ResponseEntity<>(apiError, httpStatus);
    }
}
