package com.sai.sprinto.policy.service.policy.abstractFactory.context;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.entity.sql.CustomerTemplate;
import com.sai.sprinto.policy.models.user.UserPolicy;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import lombok.Data;

import java.util.List;

@Data
public class UserPolicyContext {
    private UserPolicyType type;
    private String userId;
    private boolean modified;
    private List<String> selectedPolicyIds;
    private List<CustomerTemplate> customerTemplates;
    private List<UserPolicy> userPolicies;
    private List<Policy> approvedPolicies;
}
