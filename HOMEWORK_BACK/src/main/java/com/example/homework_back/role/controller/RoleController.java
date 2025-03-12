package com.example.homework_back.role.controller;

import com.example.homework_back.common.config.property.response.CommonResponse;
import com.example.homework_back.common.config.property.success.SuccessCode;
import com.example.homework_back.role.dto.request.RoleRequestDto;
import com.example.homework_back.role.dto.request.RoleUpdateRequestDto;
import com.example.homework_back.role.dto.response.RoleResponseDto;
import com.example.homework_back.role.service.RoleService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
    // todo: 모든 역할 조회 api 구현
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public ResponseEntity<CommonResponse<RoleResponseDto>> createRole(
            @RequestBody RoleRequestDto roleRequestDto) {
        RoleResponseDto response = roleService.createRole(roleRequestDto);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<CommonResponse<RoleResponseDto>> getRole(@PathVariable Long id) {
        RoleResponseDto response = roleService.getRole(id);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @GetMapping("/roles")
    public ResponseEntity<CommonResponse<List<RoleResponseDto>>> getAllRoles() {
        List<RoleResponseDto> response = roleService.getAllRoles();
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<CommonResponse<RoleResponseDto>> updateRole(@PathVariable Long id,
            @RequestBody RoleUpdateRequestDto roleUpdateRequestDto) {
        RoleResponseDto response = roleService.updateRole(id, roleUpdateRequestDto);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, null));
    }

    @PostMapping("/roles/{roleId}/permissions/{permissionId}")
    public ResponseEntity<CommonResponse<Void>> addPermissionToRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        roleService.addPermissionToRole(roleId, permissionId);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, null));
    }

    @DeleteMapping("/roles/{roleId}/permissions/{permissionId}")
    public ResponseEntity<CommonResponse<Void>> removePermissionFromRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        roleService.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, null));
    }
}
