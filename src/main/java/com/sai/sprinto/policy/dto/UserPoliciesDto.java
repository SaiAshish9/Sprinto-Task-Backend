package com.sai.sprinto.policy.dto;

import com.sai.sprinto.policy.enums.Role;
import lombok.Data;

@Data
public class UserPoliciesDto {
    private String userId;
    private Role role;
    private boolean modified;
}
