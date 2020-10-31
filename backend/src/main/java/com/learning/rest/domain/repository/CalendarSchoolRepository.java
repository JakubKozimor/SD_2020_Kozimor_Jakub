package com.learning.rest.domain.repository;


import com.learning.rest.domain.entity.CalendarSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarSchoolRepository extends JpaRepository<CalendarSchool, Long> {
}
