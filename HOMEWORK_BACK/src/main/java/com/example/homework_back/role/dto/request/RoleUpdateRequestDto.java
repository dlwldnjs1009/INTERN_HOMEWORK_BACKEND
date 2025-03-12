package com.example.homework_back.role.dto.request;

import com.example.homework_back.permission.entity.Permission;
import com.example.homework_back.role.entity.RoleType;
import java.util.List;
import lombok.Getter;

@Getter
public class RoleUpdateRequestDto {
//    private List<Long> permissionId;
    private RoleType name;
    private String description;
}
