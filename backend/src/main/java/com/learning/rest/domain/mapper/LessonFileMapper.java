package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.homework.HomeworkFileDto;
import com.learning.rest.domain.entity.HomeworkFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HomeworkFileMapper {

    @Mapping(target = "fileContent", ignore = true)
    HomeworkFile toHomeworkFile(HomeworkFileDto homeworkFileDto);
}
