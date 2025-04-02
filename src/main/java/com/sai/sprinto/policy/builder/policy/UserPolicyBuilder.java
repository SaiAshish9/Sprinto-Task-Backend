package com.sai.sprinto.policy.builder.policy;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.enums.acknowledgement.AcknowledgementType;
import com.sai.sprinto.policy.models.user.UserPolicy;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UtilityClass
public class UserPolicyBuilder {

    public static List<UserPolicy> createPolicies(List<Policy> policies, List<String> acknowledgedPolicyIds, Map<String, AcknowledgementType> acknowledgementTypeMap) {
        List<UserPolicy> userPolicies = new ArrayList<>();

        for (Policy policy : policies) {
            UserPolicy userPolicy = new UserPolicy();
            userPolicy.setId(policy.getId());
            userPolicy.setName(policy.getName());
            userPolicy.setType(policy.getType());
            userPolicy.setAcknowledged(acknowledgedPolicyIds.contains(policy.getId()));
            if (acknowledgementTypeMap.get(policy.getId()) != null) {
                userPolicy.setAcknowledgementType(acknowledgementTypeMap.get(policy.getId()));
            } else {
                if (policy.getVersion() > 1) {
                    userPolicy.setAcknowledgementType(AcknowledgementType.PERIODIC);
                } else if(policy.isCreatedByCustomer()) {
                    userPolicy.setAcknowledgementType(AcknowledgementType.CUSTOMER);
                } else {
                    userPolicy.setAcknowledgementType(AcknowledgementType.MANUAL);
                }
            }
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
