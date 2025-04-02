package com.sai.sprinto.policy.wrapper.policy.service;

import com.sai.sprinto.policy.builder.policy.UserPolicyContextBuilder;
import com.sai.sprinto.policy.enums.user.Role;
import com.sai.sprinto.policy.models.user.UserPolicy;
import com.sai.sprinto.policy.service.dp.DpPropertyResolver;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import com.sai.sprinto.policy.service.policy.abstractFactory.provider.UserPolicyProvider;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.UserPolicyService;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.legacy.LegacyPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserPolicyServiceWrapper {

    private final DpPropertyResolver dpPropertyResolver;
    private final LegacyPolicyService legacyPolicyService;
    private final UserPolicyProvider userPolicyProvider;

    public List<UserPolicy> getUserPolicies(String userId, Role role, boolean modified) throws Exception {
        List<UserPolicy> userPolicies;

        Map<Role, UserPolicyType> userPolicyTypeMap = new HashMap<>() {{
            put(Role.ADMIN, UserPolicyType.ADMIN_POLICY);
            put(Role.CUSTOMER, UserPolicyType.CUSTOMER_POLICY);
            put(Role.CXO, UserPolicyType.CXO_POLICY);
            put(Role.ENGINEER, UserPolicyType.ENGINEER_POLICY);
            put(Role.HR, UserPolicyType.HR_POLICY);
        }};

        if (dpPropertyResolver.isUserPolicyV2Enabled()) {
            UserPolicyType userPolicyType = userPolicyTypeMap.get(role);
            UserPolicyService userPolicyService = userPolicyProvider.getProvider(userPolicyType);
            UserPolicyContext userPolicyContext = UserPolicyContextBuilder.buildUserPolicyContext(userId, role, modified);
            userPolicyService.process(userPolicyContext);
            userPolicies = userPolicyContext.getUserPolicies();
        } else {
            userPolicies = legacyPolicyService.getUserPolicies(userId, role, modified);
        }

        return userPolicies;
    }
}