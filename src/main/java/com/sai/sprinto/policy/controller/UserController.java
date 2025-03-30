package com.sai.sprinto.policy.controller;

import com.sai.sprinto.policy.dto.PostResponseDto;
import com.sai.sprinto.policy.dto.UserRequestDto;
import com.sai.sprinto.policy.entity.sql.User;
import com.sai.sprinto.policy.service.UserService;
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
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);
        return ResponseEntity.ok(PostResponseDto
                .builder()
                .message("User Created")
                .statusCode(HttpStatus.OK.value())
                .build());
    }

}
