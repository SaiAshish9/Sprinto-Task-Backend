package com.sai.sprinto.policy.service.policy.abstractFactory.service.admin;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.repository.policy.PolicyRepository;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.admin.AdminPolicyContext;
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
public class AdminPolicyService extends AbstractUserPolicyService<AdminPolicyContext> {
    private final PolicyRepository policyRepository;

    @Override
    public UserPolicyType getType() {
        return UserPolicyType.ADMIN_POLICY;
    }

    @Override
    protected void populateApprovedPolicies(AdminPolicyContext context) {
        List<Policy> approvedPolicies;
        boolean modified = context.isModified();

        if (modified) {
            approvedPolicies = policyRepository.findByVersionGreaterThan(1);
        } else {
            approvedPolicies = policyRepository.findAll();
        }
        context.setApprovedPolicies(approvedPolicies);
    }

}
