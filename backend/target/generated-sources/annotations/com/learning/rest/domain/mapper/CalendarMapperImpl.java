package com.learning.rest.domain.mapper;

import com.learning.rest.domain.dto.calendar.CalendarDto;
import com.learning.rest.domain.entity.BaseCalendar;
import com.learning.rest.domain.entity.CalendarSchool;
import java.sql.Date;
import java.time.ZoneOffset;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T22:46:18+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class CalendarMapperImpl implements CalendarMapper {

    @Override
    public CalendarDto toCalendarDto(CalendarSchool calendarSchool) {
        if ( calendarSchool == null ) {
            return null;
        }

        CalendarDto calendarDto = new CalendarDto();

        Date date = calendarSchoolDayDate( calendarSchool );
        if ( date != null ) {
            calendarDto.setStart( date.toLocalDate() );
        }
        calendarDto.setCalendarSchoolId( calendarSchool.getCalendarSchoolId() );

        return calendarDto;
    }

    private Date calendarSchoolDayDate(CalendarSchool calendarSchool) {
        if ( calendarSchool == null ) {
            return null;
        }
        BaseCalendar day = calendarSchool.getDay();
        if ( day == null ) {
            return null;
        }
        Date date = day.getDate();
        if ( date == null ) {
            return null;
        }
        return date;
    }
}
