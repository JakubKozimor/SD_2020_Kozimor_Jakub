package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDetailsDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerDto;
import com.learning.rest.domain.dto.homeworkAnswer.HomeworkAnswerUserDetailsDto;
import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.HomeworkAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HomeworkAnswerMapper {

    @Mapping(target = "files", ignore = true)
    @Mapping(target = "homeworkAnswerId", source = "homeworkAnswerId")
    HomeworkAnswer toHomeworkAnswer(HomeworkAnswerDto homeworkAnswerDto);

    HomeworkAnswerDetailsDto toHomeworkAnswerDetailsDto(HomeworkAnswer homeworkAnswer);

    @Mapping(target = "userFirstName", source = "student.firstName")
    @Mapping(target = "userLastName", source = "student.lastName")
    HomeworkAnswerUserDetailsDto toHomeworkAnswerUserDetailsDto(HomeworkAnswer homework);
}
