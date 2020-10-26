package com.learning.rest.domain.dto.subject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SubjectFileDto {
    private Long subjectFileId;
    private String fileName;
    private String fileContent;
}