package com.learning.rest.domain.dto.live.homework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LiveHomeworkAnswerFileDto {
    private Long liveHomeworkAnswerFileId;
    private String fileName;
    private String fileContent;
}
