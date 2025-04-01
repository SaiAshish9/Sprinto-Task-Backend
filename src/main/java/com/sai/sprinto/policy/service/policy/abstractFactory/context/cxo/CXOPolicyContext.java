package com.sai.sprinto.policy.service.policy.abstractFactory.context.cxo;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import lombok.Data;

@Data
public class CXOPolicyContext extends UserPolicyContext {
    private UserPolicyType type = UserPolicyType.CXO_POLICY;
}
