package com.learning.rest.domain.dto.calendar;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class CalendarDto {

    private Long calendarSchoolId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    private String title;
}
