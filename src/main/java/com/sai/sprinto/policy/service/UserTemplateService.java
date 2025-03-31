package com.sai.sprinto.policy.service;

import com.sai.sprinto.policy.builder.CustomerTemplateBuilder;
import com.sai.sprinto.policy.dto.UserTemplateRequestDto;
import com.sai.sprinto.policy.repository.UserTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTemplateService {

    private final UserTemplateRepository userTemplateRepository;

    public void createUserTemplate(UserTemplateRequestDto userTemplateRequestDto) {
        userTemplateRepository.save(CustomerTemplateBuilder.createCustomerTemplate(userTemplateRequestDto));
    }

}
