package com.sai.sprinto.policy.builder;

import com.sai.sprinto.policy.dto.AcknowledgementRequestDto;
import com.sai.sprinto.policy.entity.sql.Acknowledgement;
import com.sai.sprinto.policy.enums.AcknowledgementType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AcknowledgementBuilder {

    public static Acknowledgement acknowledgePolicy(AcknowledgementRequestDto acknowledgementRequestDto) {
        Acknowledgement acknowledgement = new Acknowledgement();
        acknowledgement.setPolicyId(acknowledgementRequestDto.getPolicyId());
        acknowledgement.setUserId(acknowledgementRequestDto.getUserId());
        if (acknowledgementRequestDto.getCustomerId() != null) {
            acknowledgement.setCustomerId(acknowledgementRequestDto.getCustomerId());
            acknowledgement.setType(AcknowledgementType.CUSTOMER);
        }
        acknowledgement.setVersion(acknowledgementRequestDto.getVersion());
        return acknowledgement;
    }

}
