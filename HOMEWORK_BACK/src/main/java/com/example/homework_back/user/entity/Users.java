package com.example.homework_back.user.entity;

import com.example.homework_back.common.config.db.BaseEntity;
import com.example.homework_back.role.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Getter
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "user_name")
    String name;

    @Column(name = "user_email")
    String email;

    @Column(name = "user_password")
    String password;

    @Column(name = "group_no")
    int groupNo;

    @Column(name = "group_name")
    String groupName;

    @Column(name = "user_status_code")
    String statusCode;

    @Column(name = "user_last_login_datetime")
    LocalDateTime lastLoginDateTime;

    public Users updateRole(Role role) {
        this.role = role;
        return this;
    }

    public Users(Role role, String name, String email, String password, int groupNo, String groupName,
            String statusCode, LocalDateTime lastLoginDateTime) {
        this.role = role;
        this.name = name;
        this.email = email;
        this.password = password;
        this.groupNo = groupNo;
        this.groupName = groupName;
        this.statusCode = statusCode;
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public void updateFromDto(String name, String email, String password, int groupNo, String groupName, String statusCode, LocalDateTime lastLoginDateTime) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.groupNo = groupNo;
        this.groupName = groupName;
        this.statusCode = statusCode;
        this.lastLoginDateTime = lastLoginDateTime;
    }

    protected Users() { }

}
