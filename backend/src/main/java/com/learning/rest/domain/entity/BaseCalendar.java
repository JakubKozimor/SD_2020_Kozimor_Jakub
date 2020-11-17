package com.learning.rest.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "base_calendar")
@Getter
@Setter
@NoArgsConstructor
public class BaseCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long calendarId;
    private Date date;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "calendar_id")
    private List<CalendarSchool> calendarSchool = new ArrayList<>();

    public void addCalendarSchool(CalendarSchool calendarSchool) {
        if (calendarSchool == null) {
            this.calendarSchool = new ArrayList<>();
        }
        this.calendarSchool.add(calendarSchool);
    }
}
