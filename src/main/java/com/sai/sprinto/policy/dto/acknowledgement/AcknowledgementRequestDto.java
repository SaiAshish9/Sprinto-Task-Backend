package com.sai.sprinto.policy.dto.acknowledgement;

import com.sai.sprinto.policy.enums.user.Role;
import lombok.Data;

@Data
public class AcknowledgementRequestDto {
    private String userId;
    private String policyId;
    private String customerId;
    private int version;
    private Role role;
}
