package com.sai.sprinto.policy.service.policy.abstractFactory.context.admin;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import lombok.Data;

@Data
public class AdminPolicyContext extends UserPolicyContext {
    private UserPolicyType type = UserPolicyType.ADMIN_POLICY;
}
