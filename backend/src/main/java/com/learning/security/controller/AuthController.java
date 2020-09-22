package com.learning.security.controller;

import com.learning.security.payload.request.LoginRequest;
import com.learning.security.payload.request.RegisterRequest;
import com.learning.security.service.AuthService;
import com.learning.security.service.MapValidationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MapValidationService mapValidationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
        if (errorMap != null)
            return errorMap;
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
        if (errorMap != null)
            return errorMap;
        return new ResponseEntity<>(authService.register(registerRequest), HttpStatus.OK);
    }
}
