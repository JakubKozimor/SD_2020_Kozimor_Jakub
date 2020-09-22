package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.HomeworkFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkFileRepository extends JpaRepository<HomeworkFile, Long> {
}
