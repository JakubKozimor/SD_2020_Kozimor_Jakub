package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.live.homework.LiveHomeworkAnswerFileDto;
import com.learning.rest.domain.entity.LiveHomeworkAnswerFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LiveHomeworkAnswerFileMapper {

    @Mapping(target = "fileContent", ignore = true)
    LiveHomeworkAnswerFile toLiveHomeworkAnswerFile(LiveHomeworkAnswerFileDto liveHomeworkAnswerFileDto);

    @Mapping(target = "fileContent", ignore = true)
    LiveHomeworkAnswerFileDto toLiveHomeworkAnswerFileDto(LiveHomeworkAnswerFile liveHomeworkAnswerFile);
}
