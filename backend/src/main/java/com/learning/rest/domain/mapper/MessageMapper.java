package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.message.GroupMessage;
import com.learning.rest.domain.dto.message.MessageDetailsDto;
import com.learning.rest.domain.dto.message.MessageDto;
import com.learning.rest.domain.entity.Message;
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

    @Mapping(target = "userFromFirstName", source = "userFrom.firstName")
    @Mapping(target = "userFromLastName", source = "userFrom.lastName")
    @Mapping(target = "userFromEmail", source = "userFrom.email")
    @Mapping(target = "userFromId", source = "userFrom.userId")
    @Mapping(target = "userToFirstName", source = "userTo.firstName")
    @Mapping(target = "userToLastName", source = "userTo.lastName")
    @Mapping(target = "userToEmail", source = "userTo.email")
    @Mapping(target = "userToId", source = "userTo.userId")
    MessageDetailsDto toMessageDetailsDto(Message message);

    @Mapping(target = "userTo", ignore = true)
    MessageDto fromGroupMessageToMessageDto(GroupMessage groupMessage);
}
