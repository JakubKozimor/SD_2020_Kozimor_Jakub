package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.lesson.LiveHomeworkDetailsDto;
import com.learning.rest.domain.dto.live.homework.LiveHomeworkDto;
import com.learning.rest.domain.entity.LiveHomework;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LiveHomeworkMapper {


    @Mapping(target = "files", ignore = true)
    LiveHomework toLiveHomework(LiveHomeworkDto liveHomeworkDto);

    LiveHomeworkDetailsDto toLiveHomeworkDetailsDto(LiveHomework homework);
}
