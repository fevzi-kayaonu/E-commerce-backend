package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Enum_Role;
import com.workintech.ecommerce.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT r FROM Role r WHERE r.role= :role")
    Optional<Role> findByAuthority(@Param("role") Enum_Role role);
}
