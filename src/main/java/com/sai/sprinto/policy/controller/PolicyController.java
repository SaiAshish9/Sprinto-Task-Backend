package com.sai.sprinto.policy.controller;

import com.sai.sprinto.policy.dto.AcknowledgementRequestDto;
import com.sai.sprinto.policy.dto.PolicyRequestDto;
import com.sai.sprinto.policy.dto.PostResponseDto;
import com.sai.sprinto.policy.dto.UserPoliciesDto;
import com.sai.sprinto.policy.entity.mongoDB.Policy;
import com.sai.sprinto.policy.enums.Role;
import com.sai.sprinto.policy.models.UserPolicy;
import com.sai.sprinto.policy.service.policy.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/u/")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping("policies")
    public List<Policy> getAllPolicies() {
        List<Policy> policies = policyService.getAllPolicies();
        return policies;
    }

    @PostMapping("policies")
    public ResponseEntity createPolicy(@RequestBody PolicyRequestDto policyRequestDto) {
        List<Policy> policies = policyService.savePolicy(policyRequestDto.getPolicies());
        return ResponseEntity.ok(policies);
    }

    @PostMapping("user_policies")
    public List<UserPolicy> getUserPolicies(@RequestBody UserPoliciesDto userPoliciesDto) {
        List<UserPolicy> userPolicies = policyService.getUserPolicies(userPoliciesDto.getUserId(),
                userPoliciesDto.getRole(), userPoliciesDto.isModified());
        return userPolicies;
    }

    @PostMapping("acknowledge_policy")
    public ResponseEntity acknowledgePolicy(@RequestBody AcknowledgementRequestDto acknowledgementRequestDto) {
        try {
            List<UserPolicy> userPolicies = policyService.acknowledgePolicy(acknowledgementRequestDto);
            return ResponseEntity.ok(userPolicies);
        } catch (Exception e) {
            return ResponseEntity.ok(PostResponseDto.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .build());
        }
    }

    @PostMapping("approve_policy")
    public ResponseEntity approvePolicy(@RequestBody AcknowledgementRequestDto acknowledgementRequestDto) {
        try {
            if(acknowledgementRequestDto.getRole() != Role.ADMIN){
                throw new Exception("Invalid Role");
            }

            List<UserPolicy> userPolicies = policyService.approvePolicy(acknowledgementRequestDto);
            return ResponseEntity.ok(userPolicies);
        } catch (Exception e) {
            return ResponseEntity.ok(PostResponseDto.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .build());
        }
    }

    @PostMapping("requires_hr_acknowledgement")
    public ResponseEntity requiresHRAcknowledgement(@RequestBody AcknowledgementRequestDto acknowledgementRequestDto) {
        try {
            if(acknowledgementRequestDto.getRole() != Role.ADMIN){
                throw new Exception("Invalid Role");
            }

            List<UserPolicy> userPolicies = policyService.markHRAcknowledgement(acknowledgementRequestDto);
            return ResponseEntity.ok(userPolicies);
        } catch (Exception e) {
            return ResponseEntity.ok(PostResponseDto.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .build());
        }
    }

}
