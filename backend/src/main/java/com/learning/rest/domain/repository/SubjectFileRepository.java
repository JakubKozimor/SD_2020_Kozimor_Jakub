package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.SubjectFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectFileRepository extends JpaRepository<SubjectFile, Long> {
}
