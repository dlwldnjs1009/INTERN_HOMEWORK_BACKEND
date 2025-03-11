package com.example.homework_back.permission.service.impl;

import com.example.homework_back.common.config.error.exception.CustomException;
import com.example.homework_back.permission.dto.request.PermissionRequestDto;
import com.example.homework_back.permission.dto.response.PermissionResponseDto;
import com.example.homework_back.permission.entity.Permission;
import com.example.homework_back.permission.mapper.PermissionMapper;
import com.example.homework_back.permission.repository.PermissionRepository;
import com.example.homework_back.permission.service.PermissionService;
import org.springframework.stereotype.Service;

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
    public PermissionResponseDto createPermission(PermissionRequestDto permissionRequestDto) {
        try {
            Permission permission = permissionMapper.toPermissionForCreate(permissionRequestDto);
            Permission savedPermission = permissionRepository.save(permission);
            return permissionMapper.toPermissionResponseDto(savedPermission);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

}
