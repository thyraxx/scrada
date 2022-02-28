package com.thyraxx.scrada.repository;

import com.thyraxx.scrada.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
