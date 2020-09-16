package com.learning.domain.mapper;

import com.learning.domain.dto.MessageFileDto;
import com.learning.domain.entity.MessageFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageFileMapper {

    @Mapping(target = "fileContent", ignore = true)
    MessageFile toMessageFile(MessageFileDto messageFileDto);
}
