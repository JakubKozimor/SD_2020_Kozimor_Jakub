package com.learning.security.service.impl;

import com.learning.exception.role.RoleNotFoundException;
import com.learning.exception.user.EmailAlreadyExistException;
import com.learning.rest.domain.entity.Role;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.entity.enums.RoleName;
import com.learning.rest.domain.repository.RoleRepository;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.security.jwt.JwtTokenProvider;
import com.learning.security.model.UserPrincipal;
import com.learning.security.payload.ApiResponse;
import com.learning.security.payload.JwtAuthenticationResponse;
import com.learning.security.payload.mapper.UserMapper;
import com.learning.security.payload.request.LoginRequest;
import com.learning.security.payload.request.RegisterRequest;
import com.learning.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setAccessToken(jwt);
        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
        response.setRole(userPrincipal.getAuthorities().stream().findAny().get().toString());
        return response;
    }

    @Override
    public ApiResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail()))
            throw new EmailAlreadyExistException();

        User user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Role userRole = roleRepository.findByName(registerRequest.getRoleName()).orElseThrow(RoleNotFoundException::new);
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);

        return new ApiResponse(true, "User registered");
    }
}
