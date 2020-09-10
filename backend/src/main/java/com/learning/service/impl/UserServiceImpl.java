package com.learning.service.impl;

import com.learning.domain.entity.Homework;
import com.learning.domain.entity.Lesson;
import com.learning.domain.entity.Subject;
import com.learning.domain.entity.User;
import com.learning.domain.repository.LessonRepository;
import com.learning.domain.repository.SubjectRepository;
import com.learning.domain.repository.UserRepository;
import com.learning.exception.LessonNotFoundException;
import com.learning.exception.SubjectNotFoundException;
import com.learning.exception.UserNotFoundException;
import com.learning.pageable.PageHelper;
import com.learning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public Page<Homework> getAllHomeworks(Long id, Pageable pageable) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        List<Homework> homeworkList = new ArrayList<>();
        user.getSubjects()
                .forEach(subject -> homeworkList.addAll(subject.getHomeworks()));
        return (Page<Homework>) PageHelper.preparePageFromList(homeworkList, pageable);
    }
}
