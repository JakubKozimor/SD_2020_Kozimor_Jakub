package com.learning.rest.domain.dto.homeworkAnswer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HomeworkAnswerUserDetailsDto {
    private Long homeworkAnswerId;
    private String userFirstName;
    private String userLastName;
    private String message;
    private String grade;
    private String comment;
}
