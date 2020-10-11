package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.Message;
import com.learning.rest.domain.entity.User;
import com.learning.rest.domain.entity.enums.MessageStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByUserToAndStatus(@Param("userTo") User user, @Param("status") MessageStatus status);
    List<Message> findAllByUserFrom(@Param("userTo") User user);
}
