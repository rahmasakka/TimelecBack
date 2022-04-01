package com.timelec.timelec.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.timelec.timelec.models.ERole;
import com.timelec.timelec.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}