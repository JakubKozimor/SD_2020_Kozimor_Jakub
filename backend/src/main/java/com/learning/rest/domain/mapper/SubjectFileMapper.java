package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.subject.SubjectFileDto;
import com.learning.rest.domain.entity.SubjectFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectFileMapper {

    @Mapping(target = "fileContent", ignore = true)
    SubjectFile toSubjectFile(SubjectFileDto subjectFileDto);
}
