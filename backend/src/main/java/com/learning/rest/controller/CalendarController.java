package com.learning.rest.controller;

import com.learning.rest.domain.dto.calendar.CalendarDto;
import com.learning.rest.domain.dto.calendar.NewEventDto;
import com.learning.rest.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
@CrossOrigin
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/all")
    public ResponseEntity<List<CalendarDto>> getCalendarBySchool(@RequestParam("schoolId") Long schoolId) {
        return new ResponseEntity<>(calendarService.getCalendarBySchool(schoolId), HttpStatus.OK);
    }

    @PostMapping("/{schoolId}")
    public ResponseEntity<Void> addNewEvent(@RequestBody NewEventDto newEvent,
                                            @PathVariable Long schoolId) {
        calendarService.addNewEvent(newEvent,schoolId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
