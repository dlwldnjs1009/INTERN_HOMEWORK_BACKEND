package com.example.homework_back.role.dto.response;

import com.example.homework_back.role.entity.RoleType;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RoleResponseDto {
    private final Long id;
    private final RoleType name;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public RoleResponseDto(Long id, RoleType name, String description, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
