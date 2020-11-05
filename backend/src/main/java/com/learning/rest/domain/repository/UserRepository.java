package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(@Param("email") String email);

    Optional<User> findByEmail(@Param("email") String email);

    Page<User> findByFirstNameContainsOrLastNameContainsOrEmailContains(@Param("firstName") String firstName,
                                                                        @Param("lastName") String lastName,
                                                                        @Param("email") String email,
                                                                        Pageable pageable);

}
