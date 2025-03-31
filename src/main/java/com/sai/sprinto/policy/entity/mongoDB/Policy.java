package com.sai.sprinto.policy.entity.mongoDB;

import com.sai.sprinto.policy.enums.PolicyType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "policies")
public class Policy {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private PolicyType type;
    private boolean createdByCustomer;
    private boolean acknowledged; // by all engineers and HRs
    private boolean requiresHRAcknowledgement;
    private boolean approved; // by the Admin (CTO)
    private double version = 1.0;
    private Map<String, Object> metadata;
    private String createdAt;
    private String updatedAt;
}
