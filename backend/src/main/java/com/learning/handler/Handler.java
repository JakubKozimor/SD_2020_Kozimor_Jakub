package com.learning.handler;

import com.learning.exception.homework.HomeworkNotFoundException;
import com.learning.exception.lesson.LessonNotFoundException;
import com.learning.exception.message.MessageFileNotFoundException;
import com.learning.exception.message.MessageNotFoundException;
import com.learning.exception.role.RoleNotFoundException;
import com.learning.exception.subject.SubjectNotFoundException;
import com.learning.exception.user.EmailAlreadyExistException;
import com.learning.exception.user.EmailNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.handler.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class Handler {

    @ExceptionHandler(value = {UserNotFoundException.class, SubjectNotFoundException.class, LessonNotFoundException.class, HomeworkNotFoundException.class, MessageNotFoundException.class, MessageFileNotFoundException.class, EmailNotFoundException.class, RoleNotFoundException.class})
    public ResponseEntity<ApiError> handleNotFound(RuntimeException exception) {
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError("NOT_FOUND", exception.getMessage(), httpStatus.value());
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(value = {EmailAlreadyExistException.class})
    public ResponseEntity<ApiError> handleConflict(RuntimeException exception) {
        final HttpStatus httpStatus = HttpStatus.CONFLICT;
        ApiError apiError = new ApiError("CONFLICT", exception.getMessage(), httpStatus.value());
        return new ResponseEntity<>(apiError, httpStatus);
    }
}
