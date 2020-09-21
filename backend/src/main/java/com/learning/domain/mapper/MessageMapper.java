package com.learning.domain.mapper;

import com.learning.domain.dto.MessageDetailsDto;
import com.learning.domain.dto.MessageDto;
import com.learning.domain.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "userFrom", ignore = true)
    @Mapping(target = "userTo", ignore = true)
    @Mapping(target = "files", ignore = true)
    Message toMessage(MessageDto messageDto);

    @Mapping(target = "userFrom", source = "userFrom.userId")
    @Mapping(target = "userTo", source = "userTo.userId")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "files", ignore = true)
    MessageDto toMessageDto(Message message);

    @Mapping(target = "firstName", source = "userFrom.firstName")
    @Mapping(target = "lastName", source = "userFrom.lastName")
    @Mapping(target = "email", source = "userFrom.email")
    MessageDetailsDto toMessageDetailsDto(Message message);
}
