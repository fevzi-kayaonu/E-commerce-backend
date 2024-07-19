package com.workintech.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles", schema = "public")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false, length = 45,name="role")
    @Enumerated(EnumType.STRING)
    private Enum_Role role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "role")
    private List<User> users ;

    @Override
    public String getAuthority() {
        return role.toString();
    }
}