package com.sai.sprinto.policy.service.policy.abstractFactory.context.engineer;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import lombok.Data;

@Data
public class EngineerPolicyContext extends UserPolicyContext {
    private UserPolicyType type = UserPolicyType.ENGINEER_POLICY;
}
