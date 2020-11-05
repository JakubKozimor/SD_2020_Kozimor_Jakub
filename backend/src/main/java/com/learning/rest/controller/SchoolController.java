package com.learning.rest.controller;

import com.learning.rest.domain.dto.school.SchoolDto;
import com.learning.rest.domain.entity.School;
import com.learning.rest.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
@CrossOrigin
public class SchoolController {

    private final SchoolService schoolService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<Page<School>> getAllSchools(Pageable pageable) {
        return new ResponseEntity<>(schoolService.getAllSchools(pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<Void> addSchool(@RequestBody SchoolDto schoolDto) {
        schoolService.addSchool(schoolDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Void> updateSchool(@RequestBody SchoolDto schoolDto) {
        schoolService.updateSchool(schoolDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/details/{schoolId}")
    public ResponseEntity<SchoolDto> getDetails(@PathVariable Long schoolId) {
        return new ResponseEntity<>(schoolService.getDetails(schoolId), HttpStatus.OK);
    }

}
