package com.example.homework_back.permission.service.impl;

import com.example.homework_back.common.config.error.enumType.ErrorCode;
import com.example.homework_back.common.config.error.exception.CustomException;
import com.example.homework_back.permission.dto.request.PermissionRequestDto;
import com.example.homework_back.permission.dto.response.PermissionResponseDto;
import com.example.homework_back.permission.entity.Permission;
import com.example.homework_back.permission.mapper.PermissionMapper;
import com.example.homework_back.permission.repository.PermissionRepository;
import com.example.homework_back.permission.service.PermissionService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class permissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public permissionServiceImpl(PermissionRepository permissionRepository,
            PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<PermissionResponseDto> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissionMapper.permissionListToResponseDtoList(permissions);
    }

    @Override
    @Transactional
    public PermissionResponseDto createPermission(PermissionRequestDto permissionRequestDto) {
        try {
            Permission permission = permissionMapper.toPermissionForCreate(permissionRequestDto);
            Permission savedPermission = permissionRepository.save(permission);
            return permissionMapper.toPermissionResponseDto(savedPermission);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionResponseDto getPermission(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_PERMISSION,
                        "권한을 찾을 수 없습니다."));
        return permissionMapper.toPermissionResponseDto(permission);
    }

    @Override
    @Transactional
    public PermissionResponseDto updatePermission(Long id,
            PermissionRequestDto permissionRequestDto) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_PERMISSION,
                        "권한을 찾을 수 없습니다."));
        permissionMapper.updatePermissionFromDto(permissionRequestDto, permission);
        Permission updatedPermission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponseDto(updatedPermission);
    }

    @Override
    @Transactional
    public void deletePermission(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_PERMISSION,
                        "권한을 찾을 수 없습니다."));
        permissionRepository.delete(permission);
    }

}
