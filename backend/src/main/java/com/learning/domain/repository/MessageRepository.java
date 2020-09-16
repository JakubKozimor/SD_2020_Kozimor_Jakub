package com.learning.domain.repository;

import com.learning.domain.entity.Message;
import com.learning.domain.entity.User;
import com.learning.domain.entity.enums.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByUserToAndStatus(@Param("userTo") User userId, @Param("status") MessageStatus status);
}
