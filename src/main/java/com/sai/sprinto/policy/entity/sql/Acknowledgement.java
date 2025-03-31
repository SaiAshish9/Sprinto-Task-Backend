package com.sai.sprinto.policy.entity.sql;

import com.sai.sprinto.policy.enums.AcknowledgementType;
import com.sai.sprinto.policy.listener.AcknowledgementListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "acknowledgements")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AcknowledgementListener.class)
public class Acknowledgement {
    @Id
    private String id;
    private String userId;
    private String policyId;
    private AcknowledgementType type = AcknowledgementType.MANUAL;
    private double version = 1.0;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
