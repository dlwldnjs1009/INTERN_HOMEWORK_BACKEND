package com.example.homework_back.user.service.impl;

import com.example.homework_back.common.config.error.enumType.ErrorCode;
import com.example.homework_back.common.config.error.exception.CustomException;
import com.example.homework_back.role.entity.Role;
import com.example.homework_back.role.repository.RoleRepository;
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
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new CustomException(
                HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다."));
        return userMapper.toUserResponseDto(user);
    }

    /**
     * 유저는 생성할 때 반드시 역할을 가지고 있어야 한다.
     * todo: 아래 메소드는 User 생성과 Role 매핑을 담당하고 있어 차후에
     * 유지보수나 확장을 위해서 더 작은 책임을 가지도록 분리가 필요하다.
     */
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        try {

            Role role = roleRepository.findById(userRequestDto.getRoleId())
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_ROLE, "역할을 찾을 수 없습니다."));

            Users user = userMapper.toUsersForCreate(userRequestDto);
            user.updateRole(role);

            Users savedUser = userRepository.save(user);
            return userMapper.toUserResponseDto(savedUser);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        Role role = roleRepository.findById(userUpdateRequestDto.getRoleId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_ROLE, "역할을 찾을 수 없습니다."));

        Users user = userRepository.findById(id).orElseThrow(() -> new CustomException(
                HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다."));

        userMapper.updateUserFromDto(userUpdateRequestDto, user);
        user.updateRole(role);
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

