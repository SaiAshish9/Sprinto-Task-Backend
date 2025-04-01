package com.sai.sprinto.policy.controller.user;

import com.sai.sprinto.policy.dto.common.PostResponseDto;
import com.sai.sprinto.policy.dto.user.UserRequestDto;
import com.sai.sprinto.policy.entity.sql.User;
import com.sai.sprinto.policy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/u/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("users")
    public List<User> getAllUsers(
            @RequestParam(value = "mimic", required = false, defaultValue = "false") boolean mimic,
            @RequestParam(value = "customerId", required = false, defaultValue = "") String customerId
            ) {
        List<User> users = userService.getAllUsers(mimic, customerId);
        return users;
    }

    @PostMapping("user")
    public ResponseEntity createUser(@RequestBody UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);
        return ResponseEntity.ok(PostResponseDto
                .builder()
                .message("User Created")
                .statusCode(HttpStatus.OK.value())
                .build());
    }

}
