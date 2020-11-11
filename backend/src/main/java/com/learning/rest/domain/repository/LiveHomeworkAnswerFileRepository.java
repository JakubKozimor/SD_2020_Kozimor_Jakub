package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.LiveHomeworkAnswerFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveHomeworkAnswerFileRepository extends JpaRepository<LiveHomeworkAnswerFile, Long> {
}
