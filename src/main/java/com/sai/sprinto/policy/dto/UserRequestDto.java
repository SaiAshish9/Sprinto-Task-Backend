package com.sai.sprinto.policy.dto;

import com.sai.sprinto.policy.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {
    private String name;
    private String profilePicUrl;
    private Role role;
}
