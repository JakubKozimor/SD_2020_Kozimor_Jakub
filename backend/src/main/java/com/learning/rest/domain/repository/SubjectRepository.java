package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.Subject;
import com.learning.rest.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByTeacher(@Param("teacher") User user);
}
