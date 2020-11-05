package com.learning.rest.service.impl;

import com.learning.exception.school.SchoolNotFoundException;
import com.learning.rest.domain.dto.school.SchoolDto;
import com.learning.rest.domain.entity.School;
import com.learning.rest.domain.mapper.SchoolMapper;
import com.learning.rest.domain.repository.SchoolRepository;
import com.learning.rest.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Override
    public Page<School> getAllSchools(Pageable pageable) {
        return schoolRepository.findAll(pageable);
    }

    @Override
    public void addSchool(SchoolDto schoolDto) {
        School school = schoolMapper.toSchool(schoolDto);
        schoolRepository.save(school);
    }

    @Override
    public void updateSchool(SchoolDto schoolDto) {
        School school = schoolRepository.findById(schoolDto.getSchoolId()).orElseThrow(SchoolNotFoundException::new);
        school.setName(schoolDto.getName());
        school.setCity(schoolDto.getCity());
        schoolRepository.save(school);
    }

    @Override
    public SchoolDto getDetails(Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(SchoolNotFoundException::new);
        return schoolMapper.toSchoolDto(school);
    }

}
