package com.sai.sprinto.policy.controller.customer;

import com.sai.sprinto.policy.dto.common.PostResponseDto;
import com.sai.sprinto.policy.dto.customer.CustomerTemplateRequestDto;
import com.sai.sprinto.policy.service.customer.CustomerTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/u/")
@RequiredArgsConstructor
public class CustomerTemplateController {

    private final CustomerTemplateService customerTemplateService;

    @PostMapping("customerTemplate")
    public ResponseEntity createUserTemplate(@RequestBody CustomerTemplateRequestDto customerTemplateRequestDto) {
        customerTemplateService.createUserTemplate(customerTemplateRequestDto);
        return ResponseEntity.ok(PostResponseDto
                .builder()
                .message("CustomerTemplate Created")
                .statusCode(HttpStatus.OK.value())
                .build());
    }

}
