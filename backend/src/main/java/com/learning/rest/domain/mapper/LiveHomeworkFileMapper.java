package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.live.homework.LiveHomeworkFileDto;
import com.learning.rest.domain.entity.LiveHomeworkFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LiveHomeworkFileMapper {

    @Mapping(target = "fileContent", ignore = true)
    LiveHomeworkFile toLiveHomeworkFile(LiveHomeworkFileDto homeworkFileDto);
}
