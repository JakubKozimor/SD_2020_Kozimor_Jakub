package com.learning.rest.service.impl;

import com.learning.exception.role.RoleNotFoundException;
import com.learning.exception.subject.SubjectNotFoundException;
import com.learning.exception.user.UserNotFoundException;
import com.learning.rest.domain.dto.user.UpdateUser;
import com.learning.rest.domain.dto.user.UserDto;
import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.entity.enums.RoleName;
import com.learning.rest.domain.mapper.UserDtoMapper;
import com.learning.rest.domain.repository.SubjectRepository;
import com.learning.rest.domain.repository.UserRepository;
import com.learning.rest.pageable.PageHelper;
import com.learning.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userMapper;
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String getUserFirstNameAndLastNameById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getFirstName() + " " + user.getLastName();
    }

    @Override
    public Page<UserDto> getUsersBySearch(String search, Pageable pageable, Long userId) {
        int minusFromTotalElements = 0;
        Page<User> users = userRepository.findByFirstNameContainsOrLastNameContainsOrEmailContains(search, search, search, pageable);
        Optional<User> isPrincipalInList = users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny();
        Optional<User> isAdmin = users.stream()
                .filter(user -> user.getRoles().stream().findAny().get().getName() == RoleName.ROLE_ADMIN)
                .findAny();
        if (isPrincipalInList.isPresent() || isAdmin.isPresent()) {
            if (isPrincipalInList.isPresent() && isAdmin.isPresent()) {
                users = userRepository.findByFirstNameContainsOrLastNameContainsOrEmailContains(search, search, search, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize() + 2));
                minusFromTotalElements = 2;
            } else {
                users = userRepository.findByFirstNameContainsOrLastNameContainsOrEmailContains(search, search, search, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize() + 1));
                minusFromTotalElements = 1;
            }

        }
        List<UserDto> collect = users.stream()
                .filter(user -> !user.getUserId().equals(userId))
                .filter(user -> user.getRoles().stream().findAny().get().getName() != RoleName.ROLE_ADMIN)
                .map(user -> {
                    UserDto userDto = userMapper.toUserDto(user);
                    userDto.setRoleName(user.getRoles().stream().findAny().orElseThrow(RoleNotFoundException::new).getName());
                    return userDto;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, users.getTotalElements() - minusFromTotalElements);
    }

    @Override
    public Page<UserDto> getAllUsersBySubject(Long subjectId, Pageable pageable) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
        List<UserDto> allUsers = subject.getStudents().stream()
                .map(user -> {
                    UserDto userDto = userMapper.toUserDto(user);
                    userDto.setRoleName(user.getRoles().stream().findAny().orElseThrow(RoleNotFoundException::new).getName());
                    return userDto;
                })
                .collect(Collectors.toList());
        return (Page<UserDto>) PageHelper.preparePageFromList(allUsers, pageable);
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

    @Override
    public void deleteUserFromSubject(Long userId, Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
        Set<User> allStudentsBySubject = subject.getStudents();
        if (!allStudentsBySubject.isEmpty()) {
            Set<User> newUsers = allStudentsBySubject.stream()
                    .filter(user -> !user.getUserId().equals(userId))
                    .collect(Collectors.toSet());
            subject.setStudents(newUsers);
            subjectRepository.save(subject);
        }
    }

    @Override
    public void updateUser(UpdateUser updateUser) {
        User user = userRepository.findById(updateUser.getUserId()).orElseThrow(UserNotFoundException::new);
        User userToUpdate = assignNewData(user,updateUser);
        userRepository.save(userToUpdate);
    }

    @Override
    public UpdateUser getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.toUpdateUser(user);
    }

    private User assignNewData(User user, UpdateUser updateUser) {
        if(!StringUtils.isEmpty(updateUser.getFirstName())){
            user.setFirstName(updateUser.getFirstName());
        }
        if(!StringUtils.isEmpty(updateUser.getLastName())){
            user.setLastName(updateUser.getLastName());
        }
        if(!StringUtils.isEmpty(updateUser.getEmail())){
            user.setEmail(updateUser.getEmail());
        }
        if(!StringUtils.isEmpty(updateUser.getPassword())){
            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        if(!StringUtils.isEmpty(updateUser.getTwitchNick())){
            user.setTwitchNick(updateUser.getTwitchNick());
        }
        return user;
    }

}
