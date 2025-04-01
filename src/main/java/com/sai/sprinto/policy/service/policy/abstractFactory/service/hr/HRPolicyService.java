package com.sai.sprinto.policy.service.policy.abstractFactory.service.hr;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.AbstractUserPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HRPolicyService extends AbstractUserPolicyService<UserPolicyContext> {
    @Override
    public UserPolicyType getType() {
        return UserPolicyType.HR_POLICY;
    }

    @Override
    protected void populateApprovedPolicies() {

    }

    @Override
    protected void populateAcknowledgedPolicyEntities() {

    }

    @Override
    protected void buildCustomerTemplatesAndSelectedPolicyIds() {

    }
}
