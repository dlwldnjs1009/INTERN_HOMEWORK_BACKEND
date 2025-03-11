package com.example.homework_back.user.service.impl;

import com.example.homework_back.common.config.error.exception.CustomException;
import com.example.homework_back.user.dto.request.UserRequestDto;
import com.example.homework_back.user.dto.response.UserResponseDto;
import com.example.homework_back.user.entity.Users;
import com.example.homework_back.user.mapper.UserMapper;
import com.example.homework_back.user.repository.UserRepository;
import com.example.homework_back.user.service.UserService;
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

    @Override
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

}

