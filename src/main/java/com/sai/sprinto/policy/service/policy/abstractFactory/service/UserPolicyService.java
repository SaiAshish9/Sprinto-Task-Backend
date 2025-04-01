package com.sai.sprinto.policy.service.policy.abstractFactory.service;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;

public interface UserPolicyService<T extends UserPolicyContext> {
    UserPolicyType getType();
    void process(T userPolicyContext);
}
