package com.learning.rest.service.impl;

import com.learning.exception.lesson.LessonNotFoundException;
import com.learning.exception.subject.SubjectNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.Lesson;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.repository.LessonRepository;
import com.learning.rest.domain.repository.SubjectRepository;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.rest.pageable.PageHelper;
import com.learning.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;


    @Override
    @Transactional
    public void addUserToSubject(Long userId, Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.addSubject(subject);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addUserToLesson(Long userId, Long lessonId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(LessonNotFoundException::new);
        user.addLesson(lesson);
        userRepository.save(user);
    }

    @Override
    public User getTeacherById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public String getUserFirstNameAndLastNameById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getFirstName() + " " + user.getLastName();
    }
}
