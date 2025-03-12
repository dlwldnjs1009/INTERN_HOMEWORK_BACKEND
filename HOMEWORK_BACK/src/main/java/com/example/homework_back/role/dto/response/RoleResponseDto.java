package com.example.homework_back.role.dto.response;

import com.example.homework_back.permission.entity.Permission;
import com.example.homework_back.role.entity.RoleType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class RoleResponseDto {
    private final Long id;
    private final RoleType name;
    private final String description;
    private final List<Long> permissionId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public RoleResponseDto(Long id, RoleType name, String description, List<Long> permissionId, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissionId = permissionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
