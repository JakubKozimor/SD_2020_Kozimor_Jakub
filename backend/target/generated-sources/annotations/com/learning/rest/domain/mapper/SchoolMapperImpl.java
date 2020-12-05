package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.school.SchoolDto;
import com.learning.rest.domain.entity.School;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-05T15:40:43+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class SchoolMapperImpl implements SchoolMapper {

    @Override
    public School toSchool(SchoolDto schoolDto) {
        if ( schoolDto == null ) {
            return null;
        }

        School school = new School();

        school.setSchoolId( schoolDto.getSchoolId() );
        school.setName( schoolDto.getName() );
        school.setCity( schoolDto.getCity() );

        return school;
    }

    @Override
    public SchoolDto toSchoolDto(School school) {
        if ( school == null ) {
            return null;
        }

        SchoolDto schoolDto = new SchoolDto();

        schoolDto.setSchoolId( school.getSchoolId() );
        schoolDto.setName( school.getName() );
        schoolDto.setCity( school.getCity() );

        return schoolDto;
    }
}
