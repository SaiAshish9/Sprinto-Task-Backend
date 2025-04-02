package com.sai.sprinto.policy.service.policy.abstractFactory.service.engineer;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.repository.policy.PolicyRepository;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.engineer.EngineerPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.AbstractUserPolicyService;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.UserPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EngineerPolicyService extends AbstractUserPolicyService<EngineerPolicyContext> {
    private final PolicyRepository policyRepository;

    @Override
    public UserPolicyType getType() {
        return UserPolicyType.ENGINEER_POLICY;
    }

    @Override
    protected void populateApprovedPolicies(EngineerPolicyContext context) {
        List<Policy> approvedPolicies;
        boolean modified = context.isModified();

        if (modified) {
            approvedPolicies = policyRepository.findByApprovedTrueAndVersionGreaterThan(1);
        } else {
            approvedPolicies = policyRepository.findByApprovedTrue();
        }
        context.setApprovedPolicies(approvedPolicies);
    }

}
