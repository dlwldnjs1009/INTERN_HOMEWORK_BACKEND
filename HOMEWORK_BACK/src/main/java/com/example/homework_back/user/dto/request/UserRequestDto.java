package com.example.homework_back.user.dto.request;

import com.example.homework_back.role.entity.Role;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private Long roleId;
    private String name;
    private String email;
    private String password;
    private int groupNo;
    private String groupName;
    private String statusCode;
    private LocalDateTime lastLoginDateTime;
}

