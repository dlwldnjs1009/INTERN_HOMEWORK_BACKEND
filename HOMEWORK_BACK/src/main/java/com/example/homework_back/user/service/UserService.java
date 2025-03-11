package com.example.homework_back.user.service;

import com.example.homework_back.user.dto.request.UserRequestDto;
import com.example.homework_back.user.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);
}
