package com.sai.sprinto.policy.dto;

import com.sai.sprinto.policy.enums.Role;
import lombok.Data;

@Data
public class UserTemplateRequestDto {
    private String userId;
    private String policyId;
    private Role role;
}
