package com.timelec.timelec.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.timelec.timelec.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User>findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
    Page<User> findByUsernameContaining(@RequestParam("username") String username, Pageable pageable);
	//Page<User> findByUsernameContaining(@RequestParam("username") String username);	
}