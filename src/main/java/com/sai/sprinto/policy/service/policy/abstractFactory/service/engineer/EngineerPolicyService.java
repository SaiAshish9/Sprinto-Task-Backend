package com.sai.sprinto.policy.service.policy.abstractFactory.service.engineer;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.AbstractUserPolicyService;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.UserPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EngineerPolicyService extends AbstractUserPolicyService<UserPolicyContext> {
    @Override
    public UserPolicyType getType() {
        return UserPolicyType.ENGINEER_POLICY;
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
