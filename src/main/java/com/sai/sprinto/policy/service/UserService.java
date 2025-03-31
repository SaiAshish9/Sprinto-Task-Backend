package com.sai.sprinto.policy.service;

import com.sai.sprinto.policy.builder.UserBuilder;
import com.sai.sprinto.policy.dto.UserRequestDto;
import com.sai.sprinto.policy.entity.sql.CustomerEmployeeMappings;
import com.sai.sprinto.policy.entity.sql.User;
import com.sai.sprinto.policy.repository.CustomerEmployeeMappingsRepository;
import com.sai.sprinto.policy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CustomerEmployeeMappingsRepository customerEmployeeMappingsRepository;

    public List<User> getAllUsers(boolean mimic, String customerId) {
        if (!mimic) {
            return userRepository.findAll();
        } else {
            List<CustomerEmployeeMappings> customerEmployeeMappings =
                    customerEmployeeMappingsRepository.findByCustomerId(customerId);
            List<User> users = new ArrayList<>();
            for (CustomerEmployeeMappings mapping : customerEmployeeMappings) {
                Optional<User> userOptional = userRepository.findById(mapping.getEmployeeId());
                if (userOptional.isPresent()) {
                    users.add(userOptional.get());
                }
            }
            return users;
        }
    }

    public void createUser(UserRequestDto userRequestDto) {
        userRepository.save(UserBuilder.createUser(userRequestDto));
    }

}

