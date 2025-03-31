package com.sai.sprinto.policy.builder;

import com.sai.sprinto.policy.dto.AcknowledgementRequestDto;
import com.sai.sprinto.policy.entity.sql.Acknowledgement;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AcknowledgementBuilder {

    public static Acknowledgement acknowledgePolicy(AcknowledgementRequestDto acknowledgementRequestDto) {
        Acknowledgement acknowledgement = new Acknowledgement();
        acknowledgement.setPolicyId(acknowledgementRequestDto.getPolicyId());
        acknowledgement.setUserId(acknowledgementRequestDto.getUserId());
        return acknowledgement;
    }

}
