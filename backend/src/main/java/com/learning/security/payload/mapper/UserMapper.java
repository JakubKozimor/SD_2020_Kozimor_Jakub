package com.learning.security.payload.mapper;

import com.learning.rest.domain.entity.User;
import com.learning.security.payload.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toUser(RegisterRequest registerRequest);
}
