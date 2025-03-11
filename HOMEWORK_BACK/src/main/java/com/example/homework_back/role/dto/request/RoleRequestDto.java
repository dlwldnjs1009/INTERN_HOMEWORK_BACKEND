package com.example.homework_back.role.dto.request;

import com.example.homework_back.role.entity.RoleType;
import lombok.Getter;

@Getter
public class RoleRequestDto {
    RoleType name;
    String description;
}
