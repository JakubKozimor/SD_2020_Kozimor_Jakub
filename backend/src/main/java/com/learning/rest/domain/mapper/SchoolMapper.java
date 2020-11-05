package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.school.SchoolDto;
import com.learning.rest.domain.entity.School;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolMapper {

    School toSchool(SchoolDto schoolDto);

    SchoolDto toSchoolDto(School school);
}
