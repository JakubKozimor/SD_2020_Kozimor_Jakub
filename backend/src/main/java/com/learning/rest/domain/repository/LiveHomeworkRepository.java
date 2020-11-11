package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.LiveHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveHomeworkRepository extends JpaRepository<LiveHomework, Long> {
}
