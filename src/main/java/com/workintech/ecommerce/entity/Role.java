package com.workintech.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@Table(name = "roles", schema = "public")
public class Role implements GrantedAuthority {
    @Id
    @Column(name="id")
    private Long id;

    @Column(nullable = false, length = 45,name="role")
    @Enumerated(EnumType.STRING)
    private Enum_Role role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "role",
            fetch=FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    public User addUser(User user){
        users.add(user);
        return user;
    }

    @Override
    public String getAuthority() {
        return role.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $role = this.getRole();
        result = result * 59 + ($role == null ? 43 : $role.hashCode());

        return result;
    }

    @Override
    public String toString() {
        Long var10000 = this.getId();
        return "Role(id=" + var10000 + ", role=" + this.getRole() +")";
    }
}