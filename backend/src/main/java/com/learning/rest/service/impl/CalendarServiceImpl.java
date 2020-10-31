package com.learning.rest.service.impl;

import com.learning.exception.calendar.DayNotFoundException;
import com.learning.exception.school.SchoolNotFoundException;
import com.learning.rest.domain.dto.calendar.CalendarDto;
import com.learning.rest.domain.dto.calendar.NewEventDto;
import com.learning.rest.domain.entity.BaseCalendar;
import com.learning.rest.domain.entity.CalendarSchool;
import com.learning.rest.domain.entity.School;
import com.learning.rest.domain.entity.enums.Week;
import com.learning.rest.domain.mapper.CalendarMapper;
import com.learning.rest.domain.repository.BaseCalendarRepository;
import com.learning.rest.domain.repository.CalendarSchoolRepository;
import com.learning.rest.domain.repository.SchoolRepository;
import com.learning.rest.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final SchoolRepository schoolRepository;
    private final CalendarMapper calendarMapper;
    private final BaseCalendarRepository baseCalendarRepository;
    private final CalendarSchoolRepository calendarSchoolRepository;

    @Override
    @Transactional
    public List<CalendarDto> getCalendarBySchool(Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(SchoolNotFoundException::new);
        List<CalendarSchool> calendarSchoolList = school.getCalendarSchool();
        return calendarSchoolList.stream()
                .map(calendarSchool -> {
                    CalendarDto calendarDto = calendarMapper.toCalendarDto(calendarSchool);
                    calendarDto.setTitle(calendarSchool.getWeek().toString());
                    calendarDto.setCalendarSchoolId(calendarSchool.getCalendarSchoolId());
                    return calendarDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addNewEvent(NewEventDto newEvent, Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(SchoolNotFoundException::new);
        LocalDate startDate = newEvent.getStart();
        Optional<BaseCalendar> endDay = baseCalendarRepository.findByDate(Date.valueOf(newEvent.getEnd()));
        if (endDay.isEmpty()) {
            this.insertAllDaysByYear(newEvent.getEnd().getYear());
        }
        for (LocalDate date = startDate; date.isBefore(newEvent.getEnd().plusDays(1)); date = date.plusDays(1)) {
            BaseCalendar baseCalendar = baseCalendarRepository.findByDate(Date.valueOf(date)).orElseThrow(DayNotFoundException::new);
            CalendarSchool calendarSchool = new CalendarSchool();
            calendarSchool.setSchool(school);
            calendarSchool.setDay(baseCalendar);
            calendarSchool.setWeek(Week.valueOf(newEvent.getTitle()));
            calendarSchoolRepository.save(calendarSchool);
        }
    }

    private void insertAllDaysByYear(int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            BaseCalendar tempDay = new BaseCalendar();
            tempDay.setDate(Date.valueOf(date));
            baseCalendarRepository.save(tempDay);
        }
    }

}
