package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.lesson.LessonFileDto;
import com.learning.rest.domain.entity.LessonFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonFileMapper {

    @Mapping(target = "fileContent", ignore = true)
    LessonFile toLessonFile(LessonFileDto lessonFileDto);
}
