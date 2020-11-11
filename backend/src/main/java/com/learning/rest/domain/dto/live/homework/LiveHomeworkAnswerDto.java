package com.learning.rest.domain.dto.live.homework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class LiveHomeworkAnswerDto {
    private Long liveHomeworkAnswerId;
    private String message;
    private Long liveHomeworkId;
    private Long studentId;
    private List<LiveHomeworkAnswerFileDto> files;
}
