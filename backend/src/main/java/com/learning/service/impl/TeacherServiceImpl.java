package com.learning.service.impl;

import com.learning.domain.entity.Subject;
import com.learning.domain.entity.User;
import com.learning.domain.repository.UserRepository;
import com.learning.exception.UserNotFoundException;
import com.learning.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllTeachersByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Set<Subject> subjects = user.getSubjects();
        return null;
    }
}
