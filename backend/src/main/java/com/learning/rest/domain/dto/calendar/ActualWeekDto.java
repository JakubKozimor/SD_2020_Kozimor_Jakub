package com.learning.rest.domain.dto.calendar;

import com.learning.rest.domain.entity.enums.Week;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ActualWeekDto {
    private Week week;
}
