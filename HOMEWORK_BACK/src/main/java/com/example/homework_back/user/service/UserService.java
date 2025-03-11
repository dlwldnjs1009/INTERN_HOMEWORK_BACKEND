package com.example.homework_back.user.service;

import com.example.homework_back.user.dto.request.UserRequestDto;
import com.example.homework_back.user.dto.request.UserUpdateRequestDto;
import com.example.homework_back.user.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface UserService {

    // todo: soft delete 한 데이터는 찾지 않도록 구현
    UserResponseDto getUser(Long id);

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto);

    void deleteUser(Long id);
}
