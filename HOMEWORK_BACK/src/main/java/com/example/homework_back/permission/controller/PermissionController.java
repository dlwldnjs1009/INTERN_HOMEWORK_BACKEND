package com.example.homework_back.permission.controller;

import com.example.homework_back.common.config.property.response.CommonResponse;
import com.example.homework_back.common.config.property.success.SuccessCode;
import com.example.homework_back.permission.dto.request.PermissionRequestDto;
import com.example.homework_back.permission.dto.response.PermissionResponseDto;
import com.example.homework_back.permission.service.PermissionService;
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
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/permissions")
    public ResponseEntity<CommonResponse<PermissionResponseDto>> createPermission(
            @RequestBody PermissionRequestDto permissionRequestDto) {
        PermissionResponseDto response = permissionService.createPermission(permissionRequestDto);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @GetMapping("/permissions")
    public ResponseEntity<CommonResponse<List<PermissionResponseDto>>> getAllPermissions() {
        List<PermissionResponseDto> response = permissionService.getAllPermissions();
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @GetMapping("/permissions/{id}")
    public ResponseEntity<CommonResponse<PermissionResponseDto>> getPermission(@PathVariable Long id) {
        PermissionResponseDto response = permissionService.getPermission(id);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @PutMapping("/permissions/{id}")
    public ResponseEntity<CommonResponse<PermissionResponseDto>> updatePermission(@PathVariable Long id,
            @RequestBody PermissionRequestDto permissionRequestDto) {
        PermissionResponseDto response = permissionService.updatePermission(id, permissionRequestDto);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, response));
    }

    @DeleteMapping("/permissions/{id}")
    public ResponseEntity<CommonResponse<Void>> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.ok(CommonResponse.of(SuccessCode.OK, null));
    }
}
