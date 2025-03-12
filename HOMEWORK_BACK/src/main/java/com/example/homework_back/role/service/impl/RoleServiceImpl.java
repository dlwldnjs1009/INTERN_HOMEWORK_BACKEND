package com.example.homework_back.role.service.impl;

import com.example.homework_back.common.config.error.enumType.ErrorCode;
import com.example.homework_back.common.config.error.exception.CustomException;
import com.example.homework_back.permission.entity.Permission;
import com.example.homework_back.permission.repository.PermissionRepository;
import com.example.homework_back.role.dto.request.RoleRequestDto;
import com.example.homework_back.role.dto.request.RoleUpdateRequestDto;
import com.example.homework_back.role.dto.response.RoleResponseDto;
import com.example.homework_back.role.entity.Role;
import com.example.homework_back.role.mapper.RoleMapper;
import com.example.homework_back.role.repository.RoleRepository;
import com.example.homework_back.role.service.RoleService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponseDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.roleListToResponseDtoList(roles);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponseDto getRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_ROLE,
                        "역할을 찾을 수 없습니다."));
        return roleMapper.toRoleResponseDto(role);
    }

    @Override
    @Transactional
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
        try {
            // todo: 역할 생성 시 RoleType enum 타입이 아닌 데이터 들어오면 오류처리 필요
            Role role = roleMapper.toRoleForCreate(roleRequestDto);
            Role savedRole = roleRepository.save(role);
            return roleMapper.toRoleResponseDto(savedRole);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    @Transactional
    public RoleResponseDto updateRole(Long id, RoleUpdateRequestDto roleUpdateRequestDto) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_ROLE,
                        "역할을 찾을 수 없습니다."));
        roleMapper.updateRoleFromDto(roleUpdateRequestDto, role);
        Role updatedRole = roleRepository.save(role);
        return roleMapper.toRoleResponseDto(updatedRole);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_ROLE,
                        "역할을 찾을 수 없습니다."));
        roleRepository.delete(role);
    }

    //아래 코드는 역할에 권한을 추가,제거하는 메소드
    @Override
    @Transactional
    public void addPermissionToRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_ROLE,
                        "역할을 찾을 수 없습니다."));
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_PERMISSION,
                        "권한을 찾을 수 없습니다."));
        role.addPermission(permission);
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void removePermissionFromRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_ROLE,
                        "역할을 찾을 수 없습니다."));
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_PERMISSION,
                        "권한을 찾을 수 없습니다."));
        role.removePermission(permission);
        roleRepository.save(role);
    }
}
