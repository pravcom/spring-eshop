package com.akhtyamov.springeshop.dao;

import com.akhtyamov.springeshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);
}
