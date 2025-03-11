package com.example.homework_back.role.dto.request;

import com.example.homework_back.role.entity.RoleType;
import lombok.Getter;

@Getter
public class RoleUpdateRequestDto {
    private RoleType name;
    private String description;
}
