package com.sai.sprinto.policy.dto.user;

import com.sai.sprinto.policy.enums.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {
    private String name;
    private String profilePicUrl;
    private Role role;
}
