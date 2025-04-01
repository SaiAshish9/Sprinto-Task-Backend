package com.sai.sprinto.policy.repository.customer;

import com.sai.sprinto.policy.entity.sql.CustomerEmployeeMappings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerEmployeeMappingsRepository extends JpaRepository<CustomerEmployeeMappings, Long>  {
    List<CustomerEmployeeMappings> findByCustomerId(String customerId);
}
