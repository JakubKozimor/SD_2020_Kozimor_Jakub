package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.HomeworkAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkAnswerRepository extends JpaRepository<HomeworkAnswer, Long> {
}
