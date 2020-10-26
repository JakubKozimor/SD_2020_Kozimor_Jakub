package com.learning.rest.service.impl;

import com.learning.exception.role.RoleNotFoundException;
import com.learning.exception.subject.SubjectNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.domain.dto.user.UserDto;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.mapper.UserDtoMapper;
import com.learning.rest.domain.repository.SubjectRepository;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userMapper;
    private final SubjectRepository subjectRepository;


    @Override
    public String getUserFirstNameAndLastNameById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getFirstName() + " " + user.getLastName();
    }

    @Override
    public Page<UserDto> getUsersBySearch(String search, Pageable pageable) {
        Page<User> users = userRepository.findByFirstNameContainsOrLastNameContainsOrEmailContains(search, search, search, pageable);
        List<UserDto> collect = users.stream()
                .map(user -> {
                    UserDto userDto = userMapper.toUserDto(user);
                    userDto.setRoleName(user.getRoles().stream().findAny().orElseThrow(RoleNotFoundException::new).getName());
                    return userDto;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, users.getTotalElements());
    }

    @Override
    public void addUsersToSubjects(Long subjectId, Set<UserDto> students) {
        if (students != null) {
            List<User> usersToAdd = students.stream()
                    .map(userDto -> userRepository.findById(userDto.getUserId()).orElseThrow(UserNotFoundException::new))
                    .collect(Collectors.toList());
            Subject subject = subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
            usersToAdd.forEach(subject::addStudent);
            subjectRepository.save(subject);
        }
    }
}
