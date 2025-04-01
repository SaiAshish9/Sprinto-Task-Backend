package com.sai.sprinto.policy.listener.acknowledgement;

import com.sai.sprinto.policy.entity.sql.Acknowledgement;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcknowledgementListener {

    @PrePersist
    public void onCreate(Acknowledgement acknowledgment) {
        log.info("Created: {}", acknowledgment);
    }

    @PreUpdate
    public void onUpdate(Acknowledgement acknowledgment) {
        log.info("Updated: {}", acknowledgment);
    }

    @PreRemove
    public void onDelete(Acknowledgement acknowledgment) {
        log.info("Deleted: {}", acknowledgment);
    }
}
