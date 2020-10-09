package com.learning.rest.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
public class RatedHomeworkDto {
    private Long homeworkId;
    private String title;
    private Date deadline;
    private String grade;
}
