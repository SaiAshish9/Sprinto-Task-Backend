package com.sai.sprinto.policy.service.policy.abstractFactory.context.customer;

import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import lombok.Data;

@Data
public class CustomerPolicyContext extends UserPolicyContext {
    private UserPolicyType type = UserPolicyType.CUSTOMER_POLICY;
}
