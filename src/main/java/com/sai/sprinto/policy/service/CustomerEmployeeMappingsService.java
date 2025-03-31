package com.sai.sprinto.policy.service;

import com.sai.sprinto.policy.builder.CustomerEmployeeMappingBuilder;
import com.sai.sprinto.policy.dto.CustomerEmployeeMappingRequestDto;
import com.sai.sprinto.policy.repository.CustomerEmployeeMappingsRepository;
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
