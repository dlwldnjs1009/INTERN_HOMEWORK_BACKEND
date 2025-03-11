package com.example.homework_back.permission.service;

import com.example.homework_back.permission.dto.request.PermissionRequestDto;
import com.example.homework_back.permission.dto.response.PermissionResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PermissionService {

    PermissionResponseDto createPermission(PermissionRequestDto permissionRequestDto);

    PermissionResponseDto getPermission(Long id);

    PermissionResponseDto updatePermission(Long id, PermissionRequestDto permissionRequestDto);
}
