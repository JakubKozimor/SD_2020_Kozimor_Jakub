package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.subject.SubjectDto;
import com.learning.rest.domain.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(target = "files", ignore = true)
    @Mapping(target = "longOfTime", ignore = true)
    Subject toSubject(SubjectDto homeworkDto);
}
