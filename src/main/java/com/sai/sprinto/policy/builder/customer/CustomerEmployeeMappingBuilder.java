package com.sai.sprinto.policy.builder.customer;

import com.sai.sprinto.policy.dto.customer.CustomerEmployeeMappingRequestDto;
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
