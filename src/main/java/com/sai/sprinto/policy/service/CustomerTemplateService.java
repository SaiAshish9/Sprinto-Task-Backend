package com.sai.sprinto.policy.service;

import com.sai.sprinto.policy.builder.CustomerTemplateBuilder;
import com.sai.sprinto.policy.dto.CustomerTemplateRequestDto;
import com.sai.sprinto.policy.repository.CustomerTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerTemplateService {

    private final CustomerTemplateRepository customerTemplateRepository;

    public void createUserTemplate(CustomerTemplateRequestDto customerTemplateRequestDto) {
        customerTemplateRepository.save(CustomerTemplateBuilder.createCustomerTemplate(customerTemplateRequestDto));
    }

}
