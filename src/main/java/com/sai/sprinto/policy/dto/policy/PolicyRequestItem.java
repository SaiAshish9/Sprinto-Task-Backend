package com.sai.sprinto.policy.dto.policy;

import com.sai.sprinto.policy.enums.policy.PolicyType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class PolicyRequestItem {
    private PolicyType type;
    private String name;
    private boolean createdByCustomer;
    private boolean acknowledged; // by all engineers and HRs
    private boolean requiresHRAcknowledgement;
    private boolean approved; // by the Admin (CTO)
    private int version;
    private Map<String, Object> metadata;
    private String createdAt;
    private String updatedAt;
}
