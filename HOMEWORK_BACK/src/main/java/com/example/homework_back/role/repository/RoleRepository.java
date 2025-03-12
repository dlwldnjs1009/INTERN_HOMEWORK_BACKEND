package com.example.homework_back.role.repository;

import com.example.homework_back.role.entity.Role;
import com.example.homework_back.user.entity.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAll();

    Optional<Role> findById(Long id);
}
