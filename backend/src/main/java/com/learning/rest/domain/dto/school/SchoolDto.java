package com.learning.rest.domain.dto.school;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SchoolDto {
    private Long schoolId;
    private String name;
    private String city;
}
