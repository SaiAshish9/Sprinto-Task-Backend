package com.sai.sprinto.policy.service;

import com.sai.sprinto.policy.builder.PolicyBuilder;
import com.sai.sprinto.policy.dto.PolicyRequestItem;
import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {
    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public void savePolicy(List<PolicyRequestItem> policyRequestItems) {
        policyRepository.saveAll(PolicyBuilder.createPolicies(policyRequestItems));
    }

}
