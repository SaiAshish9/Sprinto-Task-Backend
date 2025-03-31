package com.sai.sprinto.policy.entity.sql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "customer_employee_mappings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEmployeeMappings {
    @Id
    private String id;
    private String customerId;
    private String employeeId;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
