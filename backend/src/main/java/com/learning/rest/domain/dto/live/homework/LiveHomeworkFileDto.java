package com.learning.rest.domain.dto.live.homework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LiveHomeworkFileDto {
    private Long homeworkFileId;
    private String fileName;
    private String fileContent;
}
