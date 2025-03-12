package com.example.homework_back.role.dto.request;

import com.example.homework_back.role.entity.RoleType;
import lombok.Getter;

@Getter
public class RoleRequestDto {
    private RoleType name;
    private String description;
}
