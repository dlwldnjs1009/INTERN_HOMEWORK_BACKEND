package com.example.homework_back.permission.repository;

import com.example.homework_back.permission.entity.Permission;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findAll();

    Optional<Permission> findById(Long id);
}
