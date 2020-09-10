package com.learning.service;

import com.learning.domain.entity.User;

import java.util.List;

public interface TeacherService {
    List<User> getAllTeachersByUserId(Long id);
}
