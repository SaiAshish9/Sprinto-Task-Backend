package com.sai.sprinto.policy.repository;

import com.sai.sprinto.policy.entity.sql.CustomerTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerTemplateRepository extends JpaRepository<CustomerTemplate, Long> {
    List<CustomerTemplate> findByUserId(String userId);
}
