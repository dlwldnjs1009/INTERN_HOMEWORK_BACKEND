package com.example.homework_back.role.mapper;

import com.example.homework_back.role.dto.request.RoleRequestDto;
import com.example.homework_back.role.dto.request.RoleUpdateRequestDto;
import com.example.homework_back.role.dto.response.RoleResponseDto;
import com.example.homework_back.role.entity.Role;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponseDto toRoleResponseDto(Role role);

    Role toRoleForCreate(RoleRequestDto roleRequestDto);

    void updateRoleFromDto(RoleUpdateRequestDto dto, @MappingTarget Role role);

    @AfterMapping
    default void fromUpdate(RoleUpdateRequestDto dto, @MappingTarget Role role) {
        role.updateFromDto(
                dto.getName(),
                dto.getDescription()
        );
    }
}
