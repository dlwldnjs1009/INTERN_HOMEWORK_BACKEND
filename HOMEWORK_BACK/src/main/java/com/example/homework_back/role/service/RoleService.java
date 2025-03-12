package com.example.homework_back.role.service;

import com.example.homework_back.role.dto.request.RoleRequestDto;
import com.example.homework_back.role.dto.request.RoleUpdateRequestDto;
import com.example.homework_back.role.dto.response.RoleResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    // todo : getALL 구현해야 함.

    RoleResponseDto getRole(Long id);

    RoleResponseDto createRole(RoleRequestDto roleRequestDto);

    RoleResponseDto updateRole(Long id, RoleUpdateRequestDto roleUpdateRequestDto);

    void deleteRole(Long id);

    void addPermissionToRole(Long roleId, Long permissionId);

    void removePermissionFromRole(Long roleId, Long permissionId);
}
