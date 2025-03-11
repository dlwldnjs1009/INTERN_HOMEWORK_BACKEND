package com.example.homework_back.user.controller;

import com.example.homework_back.common.config.property.response.CommonResponse;
import com.example.homework_back.common.config.property.success.SuccessCode;
import com.example.homework_back.user.dto.request.UserRequestDto;
import com.example.homework_back.user.dto.response.UserResponseDto;
import com.example.homework_back.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<CommonResponse<UserResponseDto>> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto response = userService.createUser(userRequestDto);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<CommonResponse<UserResponseDto>> getUser(@PathVariable Long id) {
        UserResponseDto response = userService.getUser(id);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }
}

