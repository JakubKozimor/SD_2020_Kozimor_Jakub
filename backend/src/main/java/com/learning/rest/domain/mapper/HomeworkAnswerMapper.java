package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.HomeworkAnswerDto;
import com.learning.rest.domain.entity.HomeworkAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HomeworkAnswerMapper {

    @Mapping(target = "files", ignore = true)
    HomeworkAnswer toHomeworkAnswer(HomeworkAnswerDto homeworkAnswerDto);
}
