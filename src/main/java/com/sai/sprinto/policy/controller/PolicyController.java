package com.sai.sprinto.policy.controller;

import com.sai.sprinto.policy.dto.PolicyRequestDto;
import com.sai.sprinto.policy.dto.PostResponseDto;
import com.sai.sprinto.policy.dto.UserRequestDto;
import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping("/policies")
    public List<Policy> getAllPolicies() {
        List<Policy> policies = policyService.getAllPolicies();
        return policies;
    }

    @PostMapping("/policies")
    public ResponseEntity createPolicy(@RequestBody PolicyRequestDto policyRequestDto) {
        policyService.savePolicy(policyRequestDto.getPolicies());
        return ResponseEntity.ok(PostResponseDto
                .builder()
                .message("Policies Created")
                .statusCode(HttpStatus.OK.value())
                .build());
    }

}
