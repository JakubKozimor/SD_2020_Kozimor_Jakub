package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.teacher.TeacherDto;
import com.learning.rest.domain.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-11T22:25:46+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public TeacherDto toTeacherDto(User user) {
        if ( user == null ) {
            return null;
        }

        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setTeacherId( user.getUserId() );
        teacherDto.setFirstName( user.getFirstName() );
        teacherDto.setLastName( user.getLastName() );
        teacherDto.setEmail( user.getEmail() );

        return teacherDto;
    }
}
