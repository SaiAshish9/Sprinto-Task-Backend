package com.sai.sprinto.policy.service;

import com.sai.sprinto.policy.builder.AcknowledgementBuilder;
import com.sai.sprinto.policy.dto.AcknowledgementRequestDto;
import com.sai.sprinto.policy.entity.sql.Acknowledgement;
import com.sai.sprinto.policy.enums.Role;
import com.sai.sprinto.policy.repository.AcknowledgementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcknowledgementService {
    private static final long MAX_HR_ACKNOWLEDGEMENTS_COUNT = 3;
    private static final long MAX_ENGINEER_ACKNOWLEDGEMENTS_COUNT = 15;
    private final AcknowledgementRepository acknowledgementRepository;

    public List<Acknowledgement> getAllAcknowledgements() {
        return acknowledgementRepository.findAll();
    }

    public void acknowledgePolicy(AcknowledgementRequestDto acknowledgementRequestDto) throws Exception {
        Role role = acknowledgementRequestDto.getRole();
        long count = acknowledgementRepository.countByUserId(acknowledgementRequestDto.getUserId());

        if (role == Role.HR) {
            if (count >= MAX_HR_ACKNOWLEDGEMENTS_COUNT) {
                throw new Exception("HR cannot Acknowledge more than 3 policies");
            } else if (count >= MAX_ENGINEER_ACKNOWLEDGEMENTS_COUNT) {
                throw new Exception("Engineer cannot Acknowledge more than 3 policies");
            }
        }

        acknowledgementRepository.save(AcknowledgementBuilder.acknowledgePolicy(acknowledgementRequestDto));
    }

    public List<Acknowledgement> getAllUserAcknowledgements(String userId) {
        return acknowledgementRepository.findByUserId(userId);
    }

}
