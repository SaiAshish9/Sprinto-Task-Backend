package com.sai.sprinto.policy.models;

import com.sai.sprinto.policy.enums.AcknowledgementType;
import com.sai.sprinto.policy.enums.PolicyType;
import lombok.Data;

import java.util.Map;

@Data
public class UserPolicy {
    private String id;
    private String name;
    private PolicyType type;
    private boolean selectedByCustomer;
    private boolean acknowledged; // by a specific user
    private boolean acknowledgedByAll;
    private boolean requiresHRAcknowledgement;
    private AcknowledgementType acknowledgementType = AcknowledgementType.MANUAL;
    private double version = 1.0;
    private boolean upgradeRequired;
    private boolean approved;
    private Map<String, Object> metadata;
    private String createdAt;
    private String updatedAt;
}
