package com.sai.sprinto.policy.repository;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends MongoRepository<Policy, String> {
}
