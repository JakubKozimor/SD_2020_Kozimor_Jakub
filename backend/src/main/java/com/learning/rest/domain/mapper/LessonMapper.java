package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.lesson.LessonDetailsDto;
import com.learning.rest.domain.dto.lesson.LessonDto;
import com.learning.rest.domain.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "files", ignore = true)
    @Mapping(target = "url", ignore = true)
    @Mapping(target = "status", constant = "LIVE")
    Lesson toLesson(LessonDto lessonDto);

    @Mapping(target = "files", ignore = true)
    Lesson toEditLesson(LessonDto lessonDto);


    @Mapping(target = "subjectId", source = "subject.subjectId")
    @Mapping(target = "subjectName", source = "subject.name")
    @Mapping(target = "startTime", source = "subject.startTime")
    LessonDetailsDto toLessonDetailsDto(Lesson lesson);
}
