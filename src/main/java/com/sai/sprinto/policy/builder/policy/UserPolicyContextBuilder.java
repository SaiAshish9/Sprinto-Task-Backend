package com.sai.sprinto.policy.builder.policy;

import com.sai.sprinto.policy.enums.user.Role;
import com.sai.sprinto.policy.service.policy.abstractFactory.context.UserPolicyContext;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserPolicyContextBuilder {

    public static UserPolicyContext buildUserPolicyContext(String userId, Role role, boolean modified){
        UserPolicyContext userPolicyContext = new UserPolicyContext();
        userPolicyContext.setUserId(userId);
        userPolicyContext.setRole(role);
        userPolicyContext.setModified(modified);
        return userPolicyContext;
    }

}
