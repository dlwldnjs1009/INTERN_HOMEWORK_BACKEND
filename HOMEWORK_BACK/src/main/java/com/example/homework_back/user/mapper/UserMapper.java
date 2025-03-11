package com.example.homework_back.user.mapper;

import com.example.homework_back.common.config.error.enumType.ErrorCode;
import com.example.homework_back.common.config.error.exception.CustomException;
import com.example.homework_back.role.repository.RoleRepository;
import com.example.homework_back.user.dto.request.UserRequestDto;
import com.example.homework_back.user.dto.request.UserUpdateRequestDto;
import com.example.homework_back.user.dto.response.UserResponseDto;
import com.example.homework_back.user.entity.Users;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roleId", source = "role.id")
    UserResponseDto toUserResponseDto(Users user);

    Users toUsersForCreate(UserRequestDto userRequestDto);

    void updateUserFromDto(UserUpdateRequestDto dto, @MappingTarget Users user);

    @AfterMapping
    default void fromUpdate(UserUpdateRequestDto dto, @MappingTarget Users user) {
            user.updateFromDto(
                    dto.getName(),
                    dto.getEmail(),
                    dto.getPassword(),
                    dto.getGroupNo(),
                    dto.getGroupName(),
                    dto.getStatusCode(),
                    dto.getLastLoginDateTime()
            );
    }
}

