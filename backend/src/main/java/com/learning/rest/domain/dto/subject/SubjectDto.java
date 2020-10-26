package com.learning.rest.domain.dto.subject;

import com.learning.rest.domain.entity.enums.Day;
import com.learning.rest.domain.entity.enums.Week;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class SubjectDto {
    private Long subjectId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Day day;
    @Enumerated(EnumType.STRING)
    private Week week;
    private String startTime;
    private String longOfTime;
    private List<SubjectFileDto> files;
}
