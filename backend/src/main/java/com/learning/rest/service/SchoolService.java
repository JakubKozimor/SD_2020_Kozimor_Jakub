package com.learning.rest.service;

import com.learning.rest.domain.dto.school.SchoolDto;
import com.learning.rest.domain.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SchoolService {

    Page<School> getAllSchools(Pageable pageable);

    void addSchool(SchoolDto schoolDto);

    void updateSchool(SchoolDto schoolDto);

    SchoolDto getDetails(Long schoolId);
}
