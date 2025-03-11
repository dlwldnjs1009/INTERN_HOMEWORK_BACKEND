package com.example.homework_back.user.dto.response;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long id;
//    private Role role;
    private String name;
    private String email;
    private String password;
    private int groupNo;
    private String groupName;
    private String statusCode;
    private String lastLoginDateTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

