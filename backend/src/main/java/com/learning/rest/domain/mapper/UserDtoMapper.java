package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.user.UserDto;
import com.learning.rest.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mapping(target = "roleName", ignore = true)
    UserDto toUserDto(User user);
}
