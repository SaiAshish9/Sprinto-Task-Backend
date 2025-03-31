package com.sai.sprinto.policy.repository;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends MongoRepository<Policy, String> {
    List<Policy> findByApprovedTrue();
    List<Policy> findByApprovedTrueAndAcknowledgedTrue();
    List<Policy> findByApprovedTrueAndAcknowledgedFalse();
    List<Policy> findByApprovedTrueAndRequiresHRAcknowledgementTrue();
    List<Policy> findByVersionGreaterThan(int version);
    List<Policy> findByApprovedTrueAndVersionGreaterThan(int version);
    List<Policy> findByApprovedTrueAndRequiresHRAcknowledgementTrueAndVersionGreaterThan(int version);
}
