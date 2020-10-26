package com.learning.rest.domain.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UsersList {

    private Set<UserDto> students;
}
