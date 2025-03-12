package com.example.homework_back.role.mapper;

import com.example.homework_back.permission.entity.Permission;
import com.example.homework_back.role.dto.request.RoleRequestDto;
import com.example.homework_back.role.dto.request.RoleUpdateRequestDto;
import com.example.homework_back.role.dto.response.RoleResponseDto;
import com.example.homework_back.role.entity.Role;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissionId", source = "permissions", qualifiedByName = "streamPermissions")
    List<RoleResponseDto> roleListToResponseDtoList(List<Role> roles);

    @Mapping(target = "permissionId", source = "permissions", qualifiedByName = "streamPermissions")
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

    @Named("streamPermissions")
    default List<Long> streamPermissions(List<Permission> permissions) {
        return permissions.stream()
                .map(Permission::getId)
                .collect(Collectors.toList());
    }
}
