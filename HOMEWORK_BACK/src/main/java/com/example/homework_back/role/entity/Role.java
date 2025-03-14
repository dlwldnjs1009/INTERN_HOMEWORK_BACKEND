package com.example.homework_back.role.entity;


import com.example.homework_back.common.config.db.BaseEntity;
import com.example.homework_back.permission.entity.Permission;
import com.example.homework_back.user.entity.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private RoleType name;

    @Column(name = "role_description")
    private String description;

    @OneToMany(mappedBy = "role")
    private List<Users> users = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY) // 기본 패치 전략은 지연 로딩
    @JoinTable(name = "role_permission", // JPA는 내부적으로 "role_permission" 조인 테이블 생성
            joinColumns = @JoinColumn(name = "role_id"), // role_id와 permission_id를 결합한 유니크 제약 조건으로 관리
            inverseJoinColumns = @JoinColumn(name = "permission_id")) // 두 컬럼의 조합은 유일하게 관리됨.
    private List<Permission> permissions = new ArrayList<Permission>();

    public Role(RoleType name, String description) {
        this.name = name;
        this.description = description;
//        this.permissions = permissionxs;
    }

    public void updateFromDto(RoleType name, String description) {
        this.name = name;
        this.description = description;
    }

    protected Role() {
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public void removePermission(Permission permission) {
        this.permissions.remove(permission);
    }


}
