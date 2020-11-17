package com.learning.rest.service;

import com.learning.rest.domain.dto.school.SchoolDto;
import com.learning.rest.domain.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SchoolService {

    Page<School> getAllSchools(Pageable pageable);

    List<School> getAllSchoolsForSelect();

    void addSchool(SchoolDto schoolDto);

    void updateSchool(SchoolDto schoolDto);

    SchoolDto getDetails(Long schoolId);
}
