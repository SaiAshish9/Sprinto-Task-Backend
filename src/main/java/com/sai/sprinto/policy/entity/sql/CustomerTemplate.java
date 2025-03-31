package com.sai.sprinto.policy.entity.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "customer_templates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTemplate {
    @Id
    private String id;
    private String userId;
    private String policyId;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
