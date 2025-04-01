package com.sai.sprinto.policy.service.policy.abstractFactory.context.hr;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import lombok.Data;

@Data
public class HRPolicyContext extends UserPolicyContext {
    private UserPolicyType type = UserPolicyType.HR_POLICY;
}
