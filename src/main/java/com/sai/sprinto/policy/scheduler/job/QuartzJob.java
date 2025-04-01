package com.sai.sprinto.policy.scheduler.job;

import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.entity.sql.Acknowledgement;
import com.sai.sprinto.policy.enums.acknowledgement.AcknowledgementType;
import com.sai.sprinto.policy.repository.acknowledgement.AcknowledgementRepository;
import com.sai.sprinto.policy.repository.policy.PolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuartzJob implements Job {

    private final AcknowledgementRepository acknowledgementRepository;
    private final PolicyRepository policyRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<Acknowledgement> acknowledgements = acknowledgementRepository.findAll();
        log.info("Acknowledgements Count: {}", acknowledgements.size());
        acknowledgements.forEach(ack -> log.info("Acknowledgement: {}", ack));
        processJobVersions(acknowledgements);
    }

    private void processJobVersions(List<Acknowledgement> acknowledgements) {
        List<Acknowledgement> result = new ArrayList<>();

        for (Acknowledgement acknowledgement : acknowledgements) {
            String policyId = acknowledgement.getPolicyId();
            Optional<Policy> policyOptional = policyRepository.findById(policyId);

            if (policyOptional.isPresent()) {
                Policy policy = policyOptional.get();
                String updatedAtStr = policy.getUpdatedAt();

                try {
                    Instant updatedAtInstant = Instant.parse(updatedAtStr);
                    LocalDateTime updatedAt = LocalDateTime.ofInstant(updatedAtInstant, ZoneId.of("UTC"));
                    log.info("updatedAtInstant: {}", updatedAtInstant);

                    if (updatedAt.isAfter(LocalDateTime.now(ZoneId.of("UTC")).minusDays(30))) {
                        acknowledgement.setVersion(acknowledgement.getVersion() + 0.1);
                        acknowledgement.setType(AcknowledgementType.PERIODIC);
                        result.add(acknowledgement);
                    }
                } catch (Exception e) {
                    log.error("Failed to parse date: {}", updatedAtStr, e);
                }
            }
        }
        acknowledgementRepository.saveAll(result);
    }

}
