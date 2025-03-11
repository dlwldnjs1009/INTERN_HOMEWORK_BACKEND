package com.example.homework_back.user.dto.request;

import lombok.Getter;

@Getter
public class UserRequestDto {
//    private Role role;
    private String name;
    private String email;
    private String password;
    private int groupNo;
    private String groupName;
    private String statusCode;
    // "yyyy-MM-dd'T'HH:mm:ss"
    private String lastLoginDateTime;
}

