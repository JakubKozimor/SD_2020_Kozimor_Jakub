package com.learning.rest.config;

import com.learning.rest.domain.entity.BaseCalendar;
import com.learning.rest.domain.repository.BaseCalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class Config {

    private final BaseCalendarRepository baseCalendarRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void addYears() {
        Optional<BaseCalendar> lastYear = baseCalendarRepository.findByDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        Optional<BaseCalendar> actualYear = baseCalendarRepository.findByDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        if (lastYear.isEmpty()) {
            insertAllDaysByYear(2020);
        }
        if (actualYear.isEmpty()) {
            insertAllDaysByYear(2021);
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
