package com.sai.sprinto.policy.service.policy.abstractFactory.service.customer;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.UserPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerPolicyService implements UserPolicyService<UserPolicyContext> {
    @Override
    public UserPolicyType getType() {
        return UserPolicyType.CUSTOMER_POLICY;
    }

    @Override
    public void process(UserPolicyContext userPolicyContext) {

    }
}
