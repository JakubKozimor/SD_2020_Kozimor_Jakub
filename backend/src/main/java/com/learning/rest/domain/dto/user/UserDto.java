package com.learning.rest.domain.dto.user;

import com.learning.rest.domain.entity.enums.RoleName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UserDto {

    Long userId;
    String firstName;
    String lastName;
    String email;
    @Enumerated(value = EnumType.STRING)
    RoleName roleName;
}
