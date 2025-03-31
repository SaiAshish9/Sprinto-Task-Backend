package com.sai.sprinto.policy.builder;

import com.sai.sprinto.policy.dto.UserTemplateRequestDto;
import com.sai.sprinto.policy.entity.sql.CustomerTemplate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTemplateBuilder {

    public static CustomerTemplate createCustomerTemplate(UserTemplateRequestDto userTemplateRequestDto) {
        CustomerTemplate customerTemplate = new CustomerTemplate();
        customerTemplate.setUserId(userTemplateRequestDto.getUserId());
        customerTemplate.setPolicyId(userTemplateRequestDto.getPolicyId());
        return customerTemplate;
    }

}
