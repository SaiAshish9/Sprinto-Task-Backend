package com.sai.sprinto.policy.service.customer;

import com.sai.sprinto.policy.builder.customer.CustomerEmployeeMappingBuilder;
import com.sai.sprinto.policy.dto.customer.CustomerEmployeeMappingRequestDto;
import com.sai.sprinto.policy.repository.customer.CustomerEmployeeMappingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerEmployeeMappingsService {

    private final CustomerEmployeeMappingsRepository customerEmployeeMappingsRepository;

    public void createCustomerEmployeeMapping(CustomerEmployeeMappingRequestDto customerEmployeeMappingRequestDto) {
        customerEmployeeMappingsRepository.save(CustomerEmployeeMappingBuilder.createCustomerEmployeeMapping(customerEmployeeMappingRequestDto));
    }

}
