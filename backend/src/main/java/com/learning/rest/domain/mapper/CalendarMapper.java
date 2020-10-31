package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.calendar.CalendarDto;
import com.learning.rest.domain.entity.CalendarSchool;
import com.learning.rest.domain.entity.Homework;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CalendarMapper {

    @Mapping(target = "start", source = "day.date")
    CalendarDto toCalendarDto(CalendarSchool calendarSchool);

}
