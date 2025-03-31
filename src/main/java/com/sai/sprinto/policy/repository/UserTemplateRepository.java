package com.sai.sprinto.policy.repository;

import com.sai.sprinto.policy.entity.sql.CustomerTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTemplateRepository extends JpaRepository<CustomerTemplate, Long> {
}
