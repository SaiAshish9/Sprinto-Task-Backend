package com.sai.sprinto.policy.service;

import com.sai.sprinto.policy.builder.PolicyBuilder;
import com.sai.sprinto.policy.builder.UserPolicyBuilder;
import com.sai.sprinto.policy.dto.AcknowledgementRequestDto;
import com.sai.sprinto.policy.dto.PolicyRequestItem;
import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.entity.sql.Acknowledgement;
import com.sai.sprinto.policy.enums.Role;
import com.sai.sprinto.policy.models.UserPolicy;
import com.sai.sprinto.policy.repository.AcknowledgementRepository;
import com.sai.sprinto.policy.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyService {
    private final PolicyRepository policyRepository;
    private final AcknowledgementRepository acknowledgementRepository;
    private final AcknowledgementService acknowledgementService;

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public void savePolicy(List<PolicyRequestItem> policyRequestItems) {
        policyRepository.saveAll(PolicyBuilder.createPolicies(policyRequestItems));
    }

    public List<UserPolicy> getUserPolicies(String userId, Role role, boolean modified) {
        List<Policy> approvedPolicies;

        switch (role) {
            case HR -> {
                if (modified) {
                    approvedPolicies = policyRepository
                            .findByApprovedTrueAndRequiresHRAcknowledgementTrueAndVersionGreaterThan(1);
                } else {
                    approvedPolicies = policyRepository.findByApprovedTrueAndRequiresHRAcknowledgementTrue();
                }
                break;
            }
            case ADMIN -> {
                if(modified){
                    approvedPolicies = policyRepository.findByVersionGreaterThan(1);
                }else {
                    approvedPolicies = policyRepository.findAll();
                }
                break;
            }
            case CXO -> {
                approvedPolicies = policyRepository.findByApprovedTrueAndAcknowledgedFalse();
                break;
            }
            case CUSTOMER -> {
                approvedPolicies = policyRepository.findByApprovedTrueAndAcknowledgedTrue();
                break;
            }
            default -> {
                if(modified){
                    approvedPolicies = policyRepository.findByApprovedTrueAndVersionGreaterThan(1);
                }else {
                    approvedPolicies = policyRepository.findByApprovedTrue();
                }
            }
        }

        List<String> acknowledgedPolicyIds = new ArrayList<>();

        if (role != Role.ADMIN) {
            List<Acknowledgement> acknowledgements = acknowledgementRepository.findByUserId(userId);
            acknowledgedPolicyIds = acknowledgements
                    .stream()
                    .map(acknowledgement -> acknowledgement.getPolicyId())
                    .toList();
        }

        List<UserPolicy> userPolicies = UserPolicyBuilder.createPolicies(approvedPolicies, acknowledgedPolicyIds);
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
