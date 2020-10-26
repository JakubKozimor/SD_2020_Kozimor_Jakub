package com.learning.rest.domain.dto.teacher;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class TeacherDto {
    private Long teacherId;
    private String firstName;
    private String lastName;
    private String email;
    private String subject;
}
