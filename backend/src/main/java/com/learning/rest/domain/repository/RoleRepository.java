package com.learning.rest.domain.repository;

import com.learning.rest.domain.entity.Role;
import com.learning.rest.domain.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(@Param("name") RoleName roleName);
}
