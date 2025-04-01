package com.sai.sprinto.policy.repository.user;

import com.sai.sprinto.policy.entity.sql.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
