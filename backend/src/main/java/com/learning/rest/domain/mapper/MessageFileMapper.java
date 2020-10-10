package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.message.MessageFileDto;
import com.learning.rest.domain.entity.MessageFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageFileMapper {

    @Mapping(target = "fileContent", ignore = true)
    MessageFile toMessageFile(MessageFileDto messageFileDto);
}
