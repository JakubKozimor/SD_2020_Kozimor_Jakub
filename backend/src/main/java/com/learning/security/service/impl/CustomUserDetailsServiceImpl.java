package com.learning.security.service.impl;

import com.learning.exception.user.EmailNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.security.model.UserPrincipal;
import com.learning.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
        return UserPrincipal.create(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UserPrincipal.create(user);
    }
}
