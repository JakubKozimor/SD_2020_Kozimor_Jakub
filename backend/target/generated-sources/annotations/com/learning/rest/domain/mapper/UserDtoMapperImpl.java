package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.user.UpdateUser;
import com.learning.rest.domain.dto.user.UserDto;
import com.learning.rest.domain.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T22:46:19+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( user.getUserId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }

    @Override
    public UpdateUser toUpdateUser(User user) {
        if ( user == null ) {
            return null;
        }

        UpdateUser updateUser = new UpdateUser();

        updateUser.setUserId( user.getUserId() );
        updateUser.setFirstName( user.getFirstName() );
        updateUser.setLastName( user.getLastName() );
        updateUser.setEmail( user.getEmail() );
        updateUser.setPassword( user.getPassword() );
        updateUser.setTwitchNick( user.getTwitchNick() );

        return updateUser;
    }
}
