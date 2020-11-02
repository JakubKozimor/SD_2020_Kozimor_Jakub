package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.Homework;
import com.learning.rest.domain.entity.HomeworkAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkAnswerRepository extends JpaRepository<HomeworkAnswer, Long> {

    Page<HomeworkAnswer> findAllByHomeworkAndGradeIsNull(@Param("homework") Homework homework, Pageable pageable);

    Page<HomeworkAnswer> findAllByHomeworkAndGradeIsNotNull(@Param("homework") Homework homework, Pageable pageable);
}
