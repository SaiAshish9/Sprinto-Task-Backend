package com.sai.sprinto.policy.service.policy.abstractFactory.service.admin;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.admin.AdminPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.AbstractUserPolicyService;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.UserPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminPolicyService extends AbstractUserPolicyService<AdminPolicyContext> {
    @Override
    public UserPolicyType getType() {
        return UserPolicyType.ADMIN_POLICY;
    }

    @Override
    protected void populateApprovedPolicies(AdminPolicyContext context) {

    }

    @Override
    protected void populateAcknowledgedPolicyEntities(AdminPolicyContext context) {

    }

    @Override
    protected void buildCustomerTemplatesAndSelectedPolicyIds(AdminPolicyContext context) {

    }
}
