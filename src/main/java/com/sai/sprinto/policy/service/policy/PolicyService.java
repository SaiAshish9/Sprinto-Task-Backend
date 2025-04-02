package com.sai.sprinto.policy.service.policy;

import com.sai.sprinto.policy.builder.policy.PolicyBuilder;
import com.sai.sprinto.policy.dto.acknowledgement.AcknowledgementRequestDto;
import com.sai.sprinto.policy.dto.policy.PolicyRequestItem;
import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.enums.user.Role;
import com.sai.sprinto.policy.models.user.UserPolicy;
import com.sai.sprinto.policy.repository.policy.PolicyRepository;
import com.sai.sprinto.policy.service.acknowledgement.AcknowledgementService;
import com.sai.sprinto.policy.wrapper.policy.service.UserPolicyServiceWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PolicyService {
    private final PolicyRepository policyRepository;
    private final AcknowledgementService acknowledgementService;
    private final UserPolicyServiceWrapper userPolicyServiceWrapper;

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public List<Policy> savePolicy(List<PolicyRequestItem> policyRequestItems) {
        List<Policy> policies = policyRepository.saveAll(PolicyBuilder.createPolicies(policyRequestItems));
        return policies;
    }

    public List<UserPolicy> getUserPolicies(String userId, Role role, boolean modified) throws Exception {
        List<UserPolicy> userPolicies = userPolicyServiceWrapper.getUserPolicies(userId, role, modified);
        return userPolicies;
    }

    public List<UserPolicy> acknowledgePolicy(AcknowledgementRequestDto acknowledgementRequestDto) throws Exception {
        try {
            acknowledgementService.acknowledgePolicy(acknowledgementRequestDto);

            List<UserPolicy> userPolicies = getUserPolicies(acknowledgementRequestDto.getUserId(),
                    acknowledgementRequestDto.getRole(), false);
            return userPolicies;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<UserPolicy> approvePolicy(AcknowledgementRequestDto acknowledgementRequestDto) throws Exception {
        String policyId = acknowledgementRequestDto.getPolicyId();
        try {
            Optional<Policy> optionalPolicy = policyRepository.findById(policyId);
            if (optionalPolicy.isPresent()) {
                Policy policy = optionalPolicy.get();
                policy.setApproved(true);
                policyRepository.save(policy);
            } else {
                throw new RuntimeException("Policy not found with ID: " + policyId);
            }
            List<UserPolicy> userPolicies = getUserPolicies(acknowledgementRequestDto.getUserId(),
                    acknowledgementRequestDto.getRole(), false);
            return userPolicies;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<UserPolicy> markHRAcknowledgement(AcknowledgementRequestDto acknowledgementRequestDto) throws Exception {
        String policyId = acknowledgementRequestDto.getPolicyId();
        try {
            Optional<Policy> optionalPolicy = policyRepository.findById(policyId);
            if (optionalPolicy.isPresent()) {
                Policy policy = optionalPolicy.get();
                policy.setRequiresHRAcknowledgement(true);
                policyRepository.save(policy);
            } else {
                throw new RuntimeException("Policy not found with ID: " + policyId);
            }
            List<UserPolicy> userPolicies = getUserPolicies(acknowledgementRequestDto.getUserId(),
                    acknowledgementRequestDto.getRole(), false);
            return userPolicies;
        } catch (Exception e) {
            throw e;
        }
    }

}
