package com.sai.sprinto.policy.service.policy.abstractFactory.service.hr;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.repository.policy.PolicyRepository;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.hr.HRPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.AbstractUserPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HRPolicyService extends AbstractUserPolicyService<HRPolicyContext> {
    private final PolicyRepository policyRepository;

    @Override
    public UserPolicyType getType() {
        return UserPolicyType.HR_POLICY;
    }

    @Override
    protected void populateApprovedPolicies(HRPolicyContext context) {
        List<Policy> approvedPolicies;
        boolean modified = context.isModified();

        if (modified) {
            approvedPolicies = policyRepository
                    .findByApprovedTrueAndRequiresHRAcknowledgementTrueAndVersionGreaterThan(1);
        } else {
            approvedPolicies = policyRepository.findByApprovedTrueAndRequiresHRAcknowledgementTrue();
        }
        context.setApprovedPolicies(approvedPolicies);
    }

    @Override
    protected void populateAcknowledgedPolicyEntities(HRPolicyContext context) {

    }

    @Override
    protected void buildCustomerTemplatesAndSelectedPolicyIds(HRPolicyContext context) {

    }
}
