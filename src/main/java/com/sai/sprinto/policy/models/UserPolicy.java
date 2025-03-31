package com.sai.sprinto.policy.models;

import com.sai.sprinto.policy.enums.PolicyType;
import lombok.Data;

import java.util.Map;

@Data
public class UserPolicy {
    private String id;
    private String name;
    private PolicyType type;
    private boolean acknowledged; // by a specific user
    private boolean acknowledgedByAll;
    private boolean requiresHRAcknowledgement;
    private int version;
    private boolean approved;
    private Map<String, Object> metadata;
    private String createdAt;
    private String updatedAt;
}
