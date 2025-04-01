package com.sai.sprinto.policy.service.customer;

import com.sai.sprinto.policy.builder.customer.CustomerTemplateBuilder;
import com.sai.sprinto.policy.dto.customer.CustomerTemplateRequestDto;
import com.sai.sprinto.policy.repository.customer.CustomerTemplateRepository;
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
