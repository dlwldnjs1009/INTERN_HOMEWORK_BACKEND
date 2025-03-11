package com.example.homework_back.user.repository;

import com.example.homework_back.user.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findById(Long id);
}
