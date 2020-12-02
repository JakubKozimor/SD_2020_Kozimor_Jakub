package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.message.MessageFileDto;
import com.learning.rest.domain.entity.MessageFile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-02T19:33:20+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class MessageFileMapperImpl implements MessageFileMapper {

    @Override
    public MessageFile toMessageFile(MessageFileDto messageFileDto) {
        if ( messageFileDto == null ) {
            return null;
        }

        MessageFile messageFile = new MessageFile();

        messageFile.setFileName( messageFileDto.getFileName() );

        return messageFile;
    }
}
