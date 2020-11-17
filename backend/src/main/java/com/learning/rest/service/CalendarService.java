package com.learning.rest.service;

import com.learning.rest.domain.dto.calendar.ActualWeekDto;
import com.learning.rest.domain.dto.calendar.CalendarDto;
import com.learning.rest.domain.dto.calendar.NewEventDto;

import java.util.List;

public interface CalendarService {

    List<CalendarDto> getCalendarBySchool(Long schoolId);

    void addNewEvent(NewEventDto newEvent, Long schoolId);

    void removeEvent(NewEventDto newEvent, Long schoolId);

    ActualWeekDto getActualWeek(Long userId);
}
