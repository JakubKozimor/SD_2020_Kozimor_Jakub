package com.learning.security.payload.mapper;

import com.learning.rest.domain.entity.User;
import com.learning.security.payload.request.RegisterRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-05T15:40:43+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( registerRequest.getFirstName() );
        user.setLastName( registerRequest.getLastName() );
        user.setEmail( registerRequest.getEmail() );
        user.setTwitchNick( registerRequest.getTwitchNick() );

        return user;
    }
}
