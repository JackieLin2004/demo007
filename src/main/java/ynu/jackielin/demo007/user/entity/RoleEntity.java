package ynu.jackielin.demo007.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ynu.jackielin.demo007.common.BaseEntity;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Role")
public class RoleEntity extends BaseEntity {
    @Column(length = 20, nullable = false, unique = true)
    private String name;
}
