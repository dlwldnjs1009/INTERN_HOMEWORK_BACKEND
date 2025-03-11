package com.example.homework_back.user.mapper;

import com.example.homework_back.user.dto.request.UserRequestDto;
import com.example.homework_back.user.dto.response.UserResponseDto;
import com.example.homework_back.user.entity.Users;
import java.time.LocalDateTime;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toUserResponseDto(Users user);

//    @Mapping(target = "id", ignore = true)
    Users toUsersForCreate(UserRequestDto userRequestDto);

//    @Mapping(target = "id", ignore = true)
    Users toUsersForUpdate(UserRequestDto userRequestDto);

    @AfterMapping
    default void mapLastLoginDateTime(UserRequestDto dto, @MappingTarget Users user) {
        if (dto.getLastLoginDateTime() != null && !dto.getLastLoginDateTime().isEmpty()) {
            user.changeLastLoginDateTime(LocalDateTime.parse(dto.getLastLoginDateTime()));
        }
    }
}

