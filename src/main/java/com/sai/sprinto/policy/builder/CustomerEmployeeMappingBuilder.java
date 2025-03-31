package com.sai.sprinto.policy.builder;

import com.sai.sprinto.policy.dto.CustomerEmployeeMappingRequestDto;
import com.sai.sprinto.policy.entity.sql.CustomerEmployeeMappings;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerEmployeeMappingBuilder {

    public static CustomerEmployeeMappings createCustomerEmployeeMapping(CustomerEmployeeMappingRequestDto customerEmployeeMappingRequestDto) {
        CustomerEmployeeMappings customerEmployeeMappings = new CustomerEmployeeMappings();
        customerEmployeeMappings.setEmployeeId(customerEmployeeMappingRequestDto.getEmployeeId());
        customerEmployeeMappings.setCustomerId(customerEmployeeMappingRequestDto.getCustomerId());
        return customerEmployeeMappings;
    }

}
