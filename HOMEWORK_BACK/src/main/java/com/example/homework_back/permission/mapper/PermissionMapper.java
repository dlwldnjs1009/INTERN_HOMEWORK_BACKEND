package com.example.homework_back.permission.mapper;

import com.example.homework_back.permission.dto.request.PermissionRequestDto;
import com.example.homework_back.permission.dto.response.PermissionResponseDto;
import com.example.homework_back.permission.entity.Permission;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionResponseDto toPermissionResponseDto(Permission permission);

    Permission toPermissionForCreate(PermissionRequestDto permissionRequestDto);

    void updatePermissionFromDto(PermissionRequestDto dto, @MappingTarget Permission permission);

    @AfterMapping
    default void fromUpdate(PermissionRequestDto dto, @MappingTarget Permission permission) {
        permission.updateFromDto(
                dto.getName(),
                dto.getDescription()
        );
    }
}
