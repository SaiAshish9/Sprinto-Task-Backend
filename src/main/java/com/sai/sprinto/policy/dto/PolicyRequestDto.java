package com.sai.sprinto.policy.dto;

import lombok.Data;

import java.util.List;

@Data
public class PolicyRequestDto {
    List<PolicyRequestItem> policies;
}
