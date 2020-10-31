package com.learning.rest.domain.repository;


import com.learning.rest.domain.entity.BaseCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BaseCalendarRepository extends JpaRepository<BaseCalendar, Long> {

    Optional<BaseCalendar> findByDate(@Param("date") Date date);
}
