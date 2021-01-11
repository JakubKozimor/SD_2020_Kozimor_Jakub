package com.learning.rest.domain.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUser {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String twitchNick;
}
