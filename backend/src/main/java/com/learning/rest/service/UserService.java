package com.learning.rest.service;

import com.learning.rest.domain.dto.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface UserService {

    String getUserFirstNameAndLastNameById(Long userId);

    Page<UserDto> getUsersBySearch(String search, Pageable pageable);

    void addUsersToSubjects(Long subjectId, Set<UserDto> students);
}
