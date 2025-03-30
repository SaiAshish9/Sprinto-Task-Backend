package com.sai.sprinto.policy.repository;

import com.sai.sprinto.policy.entity.sql.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
