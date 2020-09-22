package com.learning.rest.service.impl;

import com.learning.rest.domain.dto.TeacherDto;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.mapper.TeacherMapper;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.pageable.PageHelper;
import com.learning.rest.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public Page<TeacherDto> getAllTeachersByUserId(Long id, Pageable pageable) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Set<Subject> subjects = user.getSubjects();
        List<TeacherDto> teachersDtoList = subjects.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
        return (Page<TeacherDto>) PageHelper.preparePageFromList(teachersDtoList, pageable);
    }

    private TeacherDto mapToUserDto(Subject subject) {
        TeacherDto teacherDto = teacherMapper.toTeacherDto(subject.getTeacher());
        teacherDto.setSubject(subject.getName());
        return teacherDto;
    }
}
