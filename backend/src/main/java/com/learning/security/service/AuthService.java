package com.learning.security.service;

import com.learning.security.payload.ApiResponse;
import com.learning.security.payload.JwtAuthenticationResponse;
import com.learning.security.payload.request.LoginRequest;
import com.learning.security.payload.request.RegisterRequest;

public interface AuthService {
    JwtAuthenticationResponse login(LoginRequest loginRequest);

    ApiResponse register(RegisterRequest registerRequest);
}
