package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.LiveHomeworkAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveHomeworkAnswerRepository extends JpaRepository<LiveHomeworkAnswer, Long> {
}
