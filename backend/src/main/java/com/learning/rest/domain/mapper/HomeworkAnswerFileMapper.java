package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerFileDto;
import com.learning.rest.domain.entity.HomeworkAnswerFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HomeworkAnswerFileMapper {

    @Mapping(target = "fileContent", ignore = true)
    HomeworkAnswerFile toHomeworkAnswerFile(HomeworkAnswerFileDto homeworkAnswerFileDto);
}
