package com.sai.sprinto.policy.builder.user;

import com.sai.sprinto.policy.dto.user.UserRequestDto;
import com.sai.sprinto.policy.entity.sql.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserBuilder {

    public static User createUser(UserRequestDto userRequestDto){
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setRole(userRequestDto.getRole());
        user.setProfilePicUrl(userRequestDto.getProfilePicUrl());
        return user;
    }

}
