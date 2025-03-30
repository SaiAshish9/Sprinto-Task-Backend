package com.sai.sprinto.policy.builder;

import com.sai.sprinto.policy.dto.PolicyRequestDto;
import com.sai.sprinto.policy.dto.PolicyRequestItem;
import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.enums.PolicyType;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UtilityClass
public class PolicyBuilder {

    public static List<Policy> createPolicies(List<PolicyRequestItem> policyItems) {
        List<Policy> policies = new ArrayList<>();

        if (policyItems.isEmpty()) {
            return new ArrayList<>();
        }

        for (PolicyRequestItem policyRequestItem : policyItems) {
//            nullSafeEmptyList(policyItems)
            Policy policy = new Policy();
            policy.setType(policyRequestItem.getType());
            policy.setAcknowledged(policyRequestItem.isAcknowledged());
            policy.setRequiresHRAcknowledgment(policyRequestItem.isRequiresHRAcknowledgment());
            policy.setApproved(policyRequestItem.isApproved());
            policy.setVersion(policyRequestItem.getVersion());
            policy.setMetadata(policyRequestItem.getMetadata());
            policy.setCreatedAt(policyRequestItem.getCreatedAt());
            policy.setUpdatedAt(policyRequestItem.getUpdatedAt());
            policies.add(policy);
        }

        return policies;
    }

}
