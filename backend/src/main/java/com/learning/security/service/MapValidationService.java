package com.learning.security.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface MapValidationService {
    ResponseEntity<?> mapValidationService(BindingResult result);
}
