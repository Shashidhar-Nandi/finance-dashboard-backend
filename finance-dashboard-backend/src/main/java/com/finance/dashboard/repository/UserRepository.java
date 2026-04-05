package com.finance.dashboard.repository;

import com.finance.dashboard.entity.Role;
import com.finance.dashboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {
	
    boolean existsByRole(Role role);

    Optional<User> findByEmail(String email);
}