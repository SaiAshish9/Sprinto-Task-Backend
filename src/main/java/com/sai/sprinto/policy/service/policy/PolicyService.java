package com.sai.sprinto.policy.service.policy;

import com.sai.sprinto.policy.builder.policy.PolicyBuilder;
import com.sai.sprinto.policy.builder.policy.UserPolicyBuilder;
import com.sai.sprinto.policy.dto.acknowledgement.AcknowledgementRequestDto;
import com.sai.sprinto.policy.dto.policy.PolicyRequestItem;
import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.entity.sql.Acknowledgement;
import com.sai.sprinto.policy.entity.sql.CustomerTemplate;
import com.sai.sprinto.policy.enums.acknowledgement.AcknowledgementType;
import com.sai.sprinto.policy.enums.user.Role;
import com.sai.sprinto.policy.models.user.UserPolicy;
import com.sai.sprinto.policy.repository.acknowledgement.AcknowledgementRepository;
import com.sai.sprinto.policy.repository.customer.CustomerTemplateRepository;
import com.sai.sprinto.policy.repository.policy.PolicyRepository;
import com.sai.sprinto.policy.service.acknowledgement.AcknowledgementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PolicyService {
    private final PolicyRepository policyRepository;
    private final AcknowledgementRepository acknowledgementRepository;
    private final AcknowledgementService acknowledgementService;
    private final CustomerTemplateRepository customerTemplateRepository;

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public List<Policy> savePolicy(List<PolicyRequestItem> policyRequestItems) {
        List<Policy> policies = policyRepository.saveAll(PolicyBuilder.createPolicies(policyRequestItems));
        return policies;
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
                if (modified) {
                    approvedPolicies = policyRepository.findByVersionGreaterThan(1);
                } else {
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
                if (modified) {
                    approvedPolicies = policyRepository.findByApprovedTrueAndVersionGreaterThan(1);
                } else {
                    approvedPolicies = policyRepository.findByApprovedTrue();
                }
            }
        }

        List<String> acknowledgedPolicyIds = new ArrayList<>();
        Map<String, AcknowledgementType> acknowledgementTypeMap = new HashMap<>();

        if (role != Role.ADMIN) {
            List<Acknowledgement> acknowledgements = acknowledgementRepository.findByUserId(userId);
            acknowledgedPolicyIds = acknowledgements
                    .stream()
                    .map(acknowledgement -> acknowledgement.getPolicyId())
                    .toList();
            for(Acknowledgement acknowledgement: acknowledgements){
                acknowledgementTypeMap.put(acknowledgement.getPolicyId(), acknowledgement.getType());
            }
        }

        List<UserPolicy> userPolicies = UserPolicyBuilder.createPolicies(approvedPolicies, acknowledgedPolicyIds, acknowledgementTypeMap);
        List<String> selectedPolicyIds = new ArrayList<>();
        List<CustomerTemplate> customerTemplates = new ArrayList<>();

        if (role == Role.CUSTOMER) {
            customerTemplates = customerTemplateRepository.findByUserId(userId);
            selectedPolicyIds = customerTemplates
                    .stream()
                    .map(acknowledgement -> acknowledgement.getPolicyId())
                    .toList();
        }

        for (UserPolicy userPolicy : userPolicies) {
            double maxVersion = customerTemplates
                    .stream()
                    .filter(template -> template.getPolicyId().equals(userPolicy.getId()))
                    .map(CustomerTemplate::getVersion)
                    .max(Double::compareTo)
                    .orElse(1.0);
            if (selectedPolicyIds.contains(userPolicy.getId())) {
                userPolicy.setSelectedByCustomer(true);
            }
            if(maxVersion > userPolicy.getVersion()){
                userPolicy.setUpgradeRequired(true);
            }
        }

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
