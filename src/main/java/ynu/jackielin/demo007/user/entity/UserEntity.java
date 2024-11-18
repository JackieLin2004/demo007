package ynu.jackielin.demo007.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ynu.jackielin.demo007.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Column(length = 20, nullable = false, unique = true)
    private String code;
    private String name;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> roles = new ArrayList<>();
}
