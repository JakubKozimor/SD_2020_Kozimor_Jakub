package com.learning.security.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
