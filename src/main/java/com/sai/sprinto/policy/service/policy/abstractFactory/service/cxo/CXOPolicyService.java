package com.sai.sprinto.policy.service.policy.abstractFactory.service.cxo;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.repository.policy.PolicyRepository;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.cxo.CXOPolicyContext;
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
public class CXOPolicyService extends AbstractUserPolicyService<CXOPolicyContext> {
    private final PolicyRepository policyRepository;

    @Override
    public UserPolicyType getType() {
        return UserPolicyType.CXO_POLICY;
    }

    @Override
    protected void populateApprovedPolicies(CXOPolicyContext context) {
        List<Policy> approvedPolicies = policyRepository.findByApprovedTrueAndAcknowledgedFalse();
        context.setApprovedPolicies(approvedPolicies);
    }

    @Override
    protected void populateAcknowledgedPolicyEntities(CXOPolicyContext context) {

    }

    @Override
    protected void buildCustomerTemplatesAndSelectedPolicyIds(CXOPolicyContext context) {

    }
}
