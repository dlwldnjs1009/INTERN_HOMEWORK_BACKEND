package com.example.homework_back.user.service.impl;

import com.example.homework_back.common.config.error.enumType.ErrorCode;
import com.example.homework_back.common.config.error.exception.CustomException;
import com.example.homework_back.user.dto.request.UserRequestDto;
import com.example.homework_back.user.dto.request.UserUpdateRequestDto;
import com.example.homework_back.user.dto.response.UserResponseDto;
import com.example.homework_back.user.entity.Users;
import com.example.homework_back.user.mapper.UserMapper;
import com.example.homework_back.user.repository.UserRepository;
import com.example.homework_back.user.service.UserService;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new CustomException(
                HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_NAME, "사용자를 찾을 수 없습니다."));
        return userMapper.toUserResponseDto(user);
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        try {
            Users user = userMapper.toUsersForCreate(userRequestDto);
            Users savedUser = userRepository.save(user);
            return userMapper.toUserResponseDto(savedUser);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        return null;
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        Users user = userRepository.findById(id).orElseThrow(() -> new CustomException(
                HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_NAME, "사용자를 찾을 수 없습니다."));
        // user엔티티에 userUpdateRequestDto의 정보를 set한 후 저장
        // 저장한 user를 userResponseDto로 변환하여 반환
        userMapper.updateUserFromDto(userUpdateRequestDto, user);
        Users updatedUser = userRepository.save(user);
        return userMapper.toUserResponseDto(updatedUser);
    }

}

