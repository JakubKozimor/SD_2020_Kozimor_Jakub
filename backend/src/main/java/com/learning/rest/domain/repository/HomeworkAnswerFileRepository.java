package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.HomeworkAnswerFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkAnswerFileRepository extends JpaRepository<HomeworkAnswerFile, Long> {
}
