package com.example.homework_back.role.service;

import com.example.homework_back.role.dto.request.RoleRequestDto;
import com.example.homework_back.role.dto.request.RoleUpdateRequestDto;
import com.example.homework_back.role.dto.response.RoleResponseDto;
import com.example.homework_back.user.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    RoleResponseDto getRole(Long id);

    RoleResponseDto createRole(RoleRequestDto roleRequestDto);

    RoleResponseDto updateRole(Long id, RoleUpdateRequestDto roleUpdateRequestDto);

    void deleteRole(Long id);
}
