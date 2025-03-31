package com.sai.sprinto.policy.controller;

import com.sai.sprinto.policy.dto.CustomerEmployeeMappingRequestDto;
import com.sai.sprinto.policy.dto.PostResponseDto;
import com.sai.sprinto.policy.service.CustomerEmployeeMappingsService;
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
public class CustomerEmployeeMappingController {

    private final CustomerEmployeeMappingsService customerEmployeeMappingsService;

    @PostMapping("customerEmployeeMapping")
    public ResponseEntity createCustomerEmployeeMapping(@RequestBody CustomerEmployeeMappingRequestDto customerEmployeeMappingRequestDto) {
        customerEmployeeMappingsService.createCustomerEmployeeMapping(customerEmployeeMappingRequestDto);
        return ResponseEntity.ok(PostResponseDto
                .builder()
                .message("CustomerEmployeeMapping Created")
                .statusCode(HttpStatus.OK.value())
                .build());
    }

}
