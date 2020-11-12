package com.learning.security.payload.request;

import com.learning.rest.domain.entity.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Email must not be null")
    @Email
    private String email;

    @NotBlank(message = "Password must not be null")
    @Size(max = 120)
    private String password;

    @Size(max = 30)
    @NotBlank(message = "First name must not be null")
    private String firstName;

    @Size(max = 30)
    @NotBlank(message = "Last name must not be null")
    private String lastName;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
