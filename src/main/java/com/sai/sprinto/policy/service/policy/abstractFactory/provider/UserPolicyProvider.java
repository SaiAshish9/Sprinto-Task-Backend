package com.sai.sprinto.policy.service.policy.abstractFactory.provider;

import com.sai.sprinto.policy.service.policy.abstractFactory.enums.UserPolicyType;
import com.sai.sprinto.policy.service.policy.abstractFactory.service.UserPolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserPolicyProvider {
    private Map<UserPolicyType, UserPolicyService> userPolicyProviders;

    @Autowired
    public void UserPolicyProvider(List<UserPolicyService> userPolicyServices) {
        userPolicyProviders = new HashMap<>();
        for (UserPolicyService userPolicyService : userPolicyServices) {
            userPolicyProviders.put(userPolicyService.getType(), userPolicyService);
        }
    }

    public UserPolicyService getProvider(UserPolicyType type) throws Exception {
        UserPolicyService userPolicyService = userPolicyProviders.get(type);
        if(userPolicyService == null){
            throw new RuntimeException("");
        }
        return userPolicyService;
    }

}
