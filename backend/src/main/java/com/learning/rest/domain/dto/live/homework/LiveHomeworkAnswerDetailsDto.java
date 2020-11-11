package com.learning.rest.domain.dto.live.homework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class LiveHomeworkAnswerDetailsDto {
    private Long liveHomeworkAnswerId;
    private String message;
    private String userFirstName;
    private String userLastName;
    private List<LiveHomeworkAnswerFileDto> files;
}
