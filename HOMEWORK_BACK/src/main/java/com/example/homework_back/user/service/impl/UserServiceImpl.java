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
                HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다."));
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

    @Transactional
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        Users user = userRepository.findById(id).orElseThrow(() -> new CustomException(
                HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다."));
        userMapper.updateUserFromDto(userUpdateRequestDto, user);
        Users updatedUser = userRepository.save(user);
        return userMapper.toUserResponseDto(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND,
                                "사용자를 찾을 수 없습니다."));
        userRepository.delete(user);
    }

}

