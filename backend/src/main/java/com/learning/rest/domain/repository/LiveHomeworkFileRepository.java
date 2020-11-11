package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.LiveHomeworkFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveHomeworkFileRepository extends JpaRepository<LiveHomeworkFile, Long> {
}
