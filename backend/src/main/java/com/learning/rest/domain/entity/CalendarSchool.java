package com.learning.rest.domain.entity;

import com.learning.rest.domain.entity.enums.Week;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "calendar_school")
@Getter
@Setter
@NoArgsConstructor
public class CalendarSchool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_school_id")
    private Long calendarSchoolId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "calendar_id")
    private BaseCalendar day;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "school_id")
    private School school;

    @Enumerated(EnumType.STRING)
    @Column(name = "week")
    private Week week;
}
