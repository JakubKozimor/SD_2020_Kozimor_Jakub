package com.learning.domain.repository;

import com.learning.domain.entity.SubjectFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectFileRepository extends JpaRepository<SubjectFile, Long> {
}
