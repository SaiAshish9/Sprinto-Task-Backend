package com.sai.sprinto.policy.service;

import com.sai.sprinto.policy.builder.UserBuilder;
import com.sai.sprinto.policy.dto.UserRequestDto;
import com.sai.sprinto.policy.entity.sql.User;
import com.sai.sprinto.policy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(UserRequestDto userRequestDto){
        userRepository.save(UserBuilder.createUser(userRequestDto));
    }

}

