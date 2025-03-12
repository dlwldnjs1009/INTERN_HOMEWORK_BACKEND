package com.example.homework_back.role.service.impl;

import com.example.homework_back.common.config.error.enumType.ErrorCode;
import com.example.homework_back.common.config.error.exception.CustomException;
import com.example.homework_back.role.dto.request.RoleRequestDto;
import com.example.homework_back.role.dto.request.RoleUpdateRequestDto;
import com.example.homework_back.role.dto.response.RoleResponseDto;
import com.example.homework_back.role.entity.Role;
import com.example.homework_back.role.mapper.RoleMapper;
import com.example.homework_back.role.repository.RoleRepository;
import com.example.homework_back.role.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
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
}
