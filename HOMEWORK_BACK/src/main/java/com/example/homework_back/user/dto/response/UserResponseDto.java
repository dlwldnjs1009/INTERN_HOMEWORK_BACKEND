package com.example.homework_back.user.dto.response;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private final Long id;
    private final Long roleId;
    private final String name;
    private final String email;
    private final String password;
    private final int groupNo;
    private final String groupName;
    private final String statusCode;
    private final LocalDateTime lastLoginDateTime;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public UserResponseDto(Long id, Long roleId, String name, String email, String password, int groupNo, String groupName, String statusCode, LocalDateTime lastLoginDateTime,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.groupNo = groupNo;
        this.groupName = groupName;
        this.statusCode = statusCode;
        this.lastLoginDateTime = lastLoginDateTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

