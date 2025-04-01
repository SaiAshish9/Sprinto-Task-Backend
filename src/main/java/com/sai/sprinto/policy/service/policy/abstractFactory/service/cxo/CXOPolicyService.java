package com.sai.sprinto.policy.service.policy.abstractFactory.service.cxo;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.UserPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CXOPolicyService implements UserPolicyService<UserPolicyContext> {
    @Override
    public UserPolicyType getType() {
        return UserPolicyType.CXO_POLICY;
    }

    @Override
    public void process(UserPolicyContext userPolicyContext) {

    }
}
