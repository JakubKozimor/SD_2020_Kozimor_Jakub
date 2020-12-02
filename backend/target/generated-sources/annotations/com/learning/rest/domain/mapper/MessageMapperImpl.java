package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.message.MessageDetailsDto;
import com.learning.rest.domain.dto.message.MessageDto;
import com.learning.rest.domain.entity.Message;
import com.learning.rest.domain.entity.MessageFile;
import com.learning.rest.domain.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-02T19:33:21+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public Message toMessage(MessageDto messageDto) {
        if ( messageDto == null ) {
            return null;
        }

        Message message = new Message();

        message.setMessageId( messageDto.getMessageId() );
        message.setTitle( messageDto.getTitle() );
        message.setContent( messageDto.getContent() );
        message.setDate( messageDto.getDate() );
        message.setStatus( messageDto.getStatus() );

        return message;
    }

    @Override
    public MessageDto toMessageDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDto messageDto = new MessageDto();

        messageDto.setDate( message.getDate() );
        messageDto.setUserFrom( messageUserFromUserId( message ) );
        messageDto.setUserTo( messageUserToUserId( message ) );
        messageDto.setMessageId( message.getMessageId() );
        messageDto.setTitle( message.getTitle() );
        messageDto.setContent( message.getContent() );
        messageDto.setStatus( message.getStatus() );

        return messageDto;
    }

    @Override
    public MessageDetailsDto toMessageDetailsDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDetailsDto messageDetailsDto = new MessageDetailsDto();

        messageDetailsDto.setUserToLastName( messageUserToLastName( message ) );
        messageDetailsDto.setUserFromLastName( messageUserFromLastName( message ) );
        messageDetailsDto.setUserToFirstName( messageUserToFirstName( message ) );
        messageDetailsDto.setUserToEmail( messageUserToEmail( message ) );
        messageDetailsDto.setUserFromId( messageUserFromUserId( message ) );
        messageDetailsDto.setUserFromEmail( messageUserFromEmail( message ) );
        messageDetailsDto.setUserToId( messageUserToUserId( message ) );
        messageDetailsDto.setUserFromFirstName( messageUserFromFirstName( message ) );
        messageDetailsDto.setMessageId( message.getMessageId() );
        messageDetailsDto.setTitle( message.getTitle() );
        messageDetailsDto.setContent( message.getContent() );
        messageDetailsDto.setDate( message.getDate() );
        List<MessageFile> list = message.getFiles();
        if ( list != null ) {
            messageDetailsDto.setFiles( new ArrayList<MessageFile>( list ) );
        }

        return messageDetailsDto;
    }

    private Long messageUserFromUserId(Message message) {
        if ( message == null ) {
            return null;
        }
        User userFrom = message.getUserFrom();
        if ( userFrom == null ) {
            return null;
        }
        Long userId = userFrom.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private Long messageUserToUserId(Message message) {
        if ( message == null ) {
            return null;
        }
        User userTo = message.getUserTo();
        if ( userTo == null ) {
            return null;
        }
        Long userId = userTo.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String messageUserToLastName(Message message) {
        if ( message == null ) {
            return null;
        }
        User userTo = message.getUserTo();
        if ( userTo == null ) {
            return null;
        }
        String lastName = userTo.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String messageUserFromLastName(Message message) {
        if ( message == null ) {
            return null;
        }
        User userFrom = message.getUserFrom();
        if ( userFrom == null ) {
            return null;
        }
        String lastName = userFrom.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String messageUserToFirstName(Message message) {
        if ( message == null ) {
            return null;
        }
        User userTo = message.getUserTo();
        if ( userTo == null ) {
            return null;
        }
        String firstName = userTo.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String messageUserToEmail(Message message) {
        if ( message == null ) {
            return null;
        }
        User userTo = message.getUserTo();
        if ( userTo == null ) {
            return null;
        }
        String email = userTo.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String messageUserFromEmail(Message message) {
        if ( message == null ) {
            return null;
        }
        User userFrom = message.getUserFrom();
        if ( userFrom == null ) {
            return null;
        }
        String email = userFrom.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String messageUserFromFirstName(Message message) {
        if ( message == null ) {
            return null;
        }
        User userFrom = message.getUserFrom();
        if ( userFrom == null ) {
            return null;
        }
        String firstName = userFrom.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }
}
