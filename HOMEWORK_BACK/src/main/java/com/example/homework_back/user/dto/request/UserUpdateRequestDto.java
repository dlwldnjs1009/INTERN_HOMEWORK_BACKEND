package com.example.homework_back.user.dto.request;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserUpdateRequestDto {
    //    private Role role;
    private String name;
    private String email;
    private String password;
    private int groupNo;
    private String groupName;
    private String statusCode;
    private LocalDateTime lastLoginDateTime;
}
