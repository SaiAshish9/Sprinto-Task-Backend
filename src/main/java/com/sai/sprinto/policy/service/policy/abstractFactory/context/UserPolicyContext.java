package com.sai.sprinto.policy.service.policy.abstractFactory.context;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.entity.sql.CustomerTemplate;
import com.sai.sprinto.policy.enums.acknowledgement.AcknowledgementType;
import com.sai.sprinto.policy.enums.user.Role;
import com.sai.sprinto.policy.models.user.UserPolicy;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserPolicyContext {
    private String userId;
    private boolean modified;
    private List<String> selectedPolicyIds;
    private List<CustomerTemplate> customerTemplates;
    private List<UserPolicy> userPolicies;
    private List<Policy> approvedPolicies;
    private List<String> acknowledgedPolicyIds;
    private Map<String, AcknowledgementType> acknowledgementTypeMap;
    private Role role;
}
