package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.HomeworkDetailsDto;
import com.learning.rest.domain.dto.HomeworkDto;
import com.learning.rest.domain.dto.RatedHomeworkDto;
import com.learning.rest.domain.entity.Homework;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HomeworkMapper {

    @Mapping(target = "subject", source = "subject.name")
    HomeworkDetailsDto toHomeworkDetailsDto(Homework homework);

    @Mapping(target = "files", ignore = true)
    @Mapping(target = "status", constant = "ACTIVE")
    Homework toHomework(HomeworkDto homeworkDto);

    RatedHomeworkDto toRatedHomework(Homework homework);
}
