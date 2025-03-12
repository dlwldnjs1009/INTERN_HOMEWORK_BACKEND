package com.example.homework_back.user.controller;

import com.example.homework_back.common.config.property.response.CommonResponse;
import com.example.homework_back.common.config.property.success.SuccessCode;
import com.example.homework_back.user.dto.request.UserRequestDto;
import com.example.homework_back.user.dto.request.UserUpdateRequestDto;
import com.example.homework_back.user.dto.response.UserResponseDto;
import com.example.homework_back.user.service.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    // todo : 모든 사용자 조회 api 구현
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<CommonResponse<UserResponseDto>> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto response = userService.createUser(userRequestDto);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @GetMapping("/users")
    public ResponseEntity<CommonResponse<List<UserResponseDto>>> getAllUsers() {
        List<UserResponseDto> response = userService.getAllUsers();
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<CommonResponse<UserResponseDto>> getUser(@PathVariable Long id) {
        UserResponseDto response = userService.getUser(id);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<CommonResponse<UserResponseDto>> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        UserResponseDto response = userService.updateUser(id, userUpdateRequestDto);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, null));
    }
}

