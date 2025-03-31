package com.sai.sprinto.policy.dto;

import com.sai.sprinto.policy.enums.Role;
import lombok.Data;

@Data
public class AcknowledgementRequestDto {
    private String userId;
    private String policyId;
    private String customerId;
    private int version;
    private Role role;
}
