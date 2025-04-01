package com.sai.sprinto.policy.dto.user;

import com.sai.sprinto.policy.enums.user.Role;
import lombok.Data;

@Data
public class UserPoliciesDto {
    private String userId;
    private Role role;
    private boolean modified;
}
