package com.learning.domain.mapper;

import com.learning.domain.dto.TeacherDto;
import com.learning.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(source = "userId", target = "teacherId")
    TeacherDto toTeacherDto(User user);
}
