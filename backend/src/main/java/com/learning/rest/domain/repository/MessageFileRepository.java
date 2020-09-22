package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.MessageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageFileRepository extends JpaRepository<MessageFile, Long> {
}
