package com.workintech.ecommerce.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false, length = 45,name="first_name")
    private String firstName;

    @Column(nullable = false, length = 45,name="last_name")
    private String lastName;

    @Column(nullable = false, length = 45, unique = true,name="email")
    private String email;

    @Column(nullable = false, length = 45,name="password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}

