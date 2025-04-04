package com.sai.sprinto.policy.builder.customer;

import com.sai.sprinto.policy.dto.customer.CustomerTemplateRequestDto;
import com.sai.sprinto.policy.entity.sql.CustomerTemplate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTemplateBuilder {

    public static CustomerTemplate createCustomerTemplate(CustomerTemplateRequestDto customerTemplateRequestDto) {
        CustomerTemplate customerTemplate = new CustomerTemplate();
        customerTemplate.setUserId(customerTemplateRequestDto.getUserId());
        customerTemplate.setPolicyId(customerTemplateRequestDto.getPolicyId());
        return customerTemplate;
    }

}
