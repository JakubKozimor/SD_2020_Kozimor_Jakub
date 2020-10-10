package com.learning.rest.domain.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
public class MessageFileDto {
    private Long lessonFileId;
    private String fileName;
    private String fileContent;
}
