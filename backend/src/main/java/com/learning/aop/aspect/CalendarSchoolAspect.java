package com.learning.aop.aspect;

import com.learning.rest.domain.entity.CalendarSchool;
import com.learning.rest.domain.repository.CalendarSchoolRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class CalendarSchoolAspect {

    private final CalendarSchoolRepository calendarSchoolRepository;

    @Before("execution( * com.learning.rest.service.impl.CalendarServiceImpl.getCalendarBySchool(..)))")
    public void removeIfSchoolIsEmpty() {
        List<CalendarSchool> all = calendarSchoolRepository.findAll();
        if (all != null && !all.isEmpty()) {
            all.forEach(calendarSchool -> {
                if (calendarSchool.getSchool() == null) {
                    calendarSchoolRepository.delete(calendarSchool);
                }
            });
        }
    }
}
