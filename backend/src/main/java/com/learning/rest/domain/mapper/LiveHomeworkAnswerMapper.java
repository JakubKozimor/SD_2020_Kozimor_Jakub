package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerDto;
import com.learning.rest.domain.entity.LiveHomeworkAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LiveHomeworkAnswerMapper {

    @Mapping(target = "files", ignore = true)
    LiveHomeworkAnswer toLiveHomeworkAnswer(LiveHomeworkAnswerDto liveHomeworkAnswerDto);

    @Mapping(target = "files", ignore = true)
    LiveHomeworkAnswerDto toLiveHomeworkAnswerDto(LiveHomeworkAnswer liveHomeworkAnswer);
}
