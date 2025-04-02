package com.sai.sprinto.policy.service.policy.abstractFactory.service.customer;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.repository.policy.PolicyRepository;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.customer.CustomerPolicyContext;
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
public class CustomerPolicyService extends AbstractUserPolicyService<CustomerPolicyContext> {
    private final PolicyRepository policyRepository;

    @Override
    public UserPolicyType getType() {
        return UserPolicyType.CUSTOMER_POLICY;
    }

    @Override
    protected void populateApprovedPolicies(CustomerPolicyContext context) {
        List<Policy> approvedPolicies = policyRepository.findByApprovedTrueAndAcknowledgedTrue();
        context.setApprovedPolicies(approvedPolicies);
    }

}
