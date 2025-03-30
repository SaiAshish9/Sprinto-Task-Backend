package com.sai.sprinto.policy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponseDto {
    private String message;
    private int statusCode;
}
