package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schools")
@Getter
@Setter
@NoArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long schoolId;
    private String name;
    private String city;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private List<CalendarSchool> calendarSchool;

    public void addCalendarSchool(CalendarSchool calendarSchool) {
        if (calendarSchool == null) {
            this.calendarSchool = new ArrayList<>();
        }
        this.calendarSchool.add(calendarSchool);
    }
}

