package com.sai.sprinto.policy.controller;

import com.sai.sprinto.policy.dto.PostResponseDto;
import com.sai.sprinto.policy.dto.UserRequestDto;
import com.sai.sprinto.policy.dto.UserTemplateRequestDto;
import com.sai.sprinto.policy.service.UserTemplateService;
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
public class UserTemplateController {

    private final UserTemplateService userTemplateService;

    @PostMapping("user")
    public ResponseEntity createUserTemplate(@RequestBody UserTemplateRequestDto userTemplateRequestDto) {
        userTemplateService.createUserTemplate(userTemplateRequestDto);
        return ResponseEntity.ok(PostResponseDto
                .builder()
                .message("UserTemplate Created")
                .statusCode(HttpStatus.OK.value())
                .build());
    }


}
