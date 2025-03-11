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
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
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

    protected Users() { }

    public Users changeLastLoginDateTime(LocalDateTime lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
        return this;
    }
}
