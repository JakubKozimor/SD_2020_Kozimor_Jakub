package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.LessonFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonFileRepository extends JpaRepository<LessonFile, Long> {
}
