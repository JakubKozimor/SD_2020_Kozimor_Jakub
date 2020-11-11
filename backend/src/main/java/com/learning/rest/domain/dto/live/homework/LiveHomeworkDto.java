package com.learning.rest.domain.dto.live.homework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class LiveHomeworkDto {
    private Long liveHomeworkId;
    private String description;
    private List<LiveHomeworkFileDto> files;
}
