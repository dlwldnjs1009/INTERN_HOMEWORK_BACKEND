package com.example.homework_back.permission.entity;

import com.example.homework_back.common.config.db.BaseEntity;
import com.example.homework_back.role.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
public class Permission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permission_name")
    private String name;

    @Column(name = "permission_description")
    private String description;

//    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
//    private List<Role> roles = new ArrayList<>();

    public Permission(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void updateFromDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected Permission() {
    }
}
