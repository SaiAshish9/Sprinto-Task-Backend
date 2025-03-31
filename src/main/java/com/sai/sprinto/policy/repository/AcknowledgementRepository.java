package com.sai.sprinto.policy.repository;


import com.sai.sprinto.policy.entity.sql.Acknowledgement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcknowledgementRepository extends JpaRepository<Acknowledgement, Long> {
    List<Acknowledgement> findByUserId(String userId);
    long countByUserId(String userId);
}
