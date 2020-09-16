package com.learning.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeacherDto {
    private Long teacherId;
    private String firstName;
    private String lastName;
    private String email;
    private String subject;
}
