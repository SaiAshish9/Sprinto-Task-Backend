package com.sai.sprinto.policy.builder;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.models.UserPolicy;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserPolicyBuilder {

    public static List<UserPolicy> createPolicies(List<Policy> policies, List<String> acknowledgedPolicyIds) {
        List<UserPolicy> userPolicies = new ArrayList<>();

        for (Policy policy : policies) {
            UserPolicy userPolicy = new UserPolicy();
            userPolicy.setId(policy.getId());
            userPolicy.setName(policy.getName());
            userPolicy.setType(policy.getType());
            userPolicy.setAcknowledged(acknowledgedPolicyIds.contains(policy.getId()));
            userPolicy.setAcknowledgedByAll(policy.isAcknowledged());
            userPolicy.setApproved(policy.isApproved());
            userPolicy.setRequiresHRAcknowledgement(policy.isRequiresHRAcknowledgement());
            userPolicy.setVersion(policy.getVersion());
            userPolicy.setMetadata(policy.getMetadata());
            userPolicy.setCreatedAt(policy.getCreatedAt());
            userPolicy.setUpdatedAt(policy.getUpdatedAt());
            userPolicies.add(userPolicy);
        }

        return userPolicies;
    }

}
