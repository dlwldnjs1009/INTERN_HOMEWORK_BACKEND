package com.example.homework_back.permission.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PermissionResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public PermissionResponseDto(Long id, String name, String description, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
