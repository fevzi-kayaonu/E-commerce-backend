package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Enum_Role;
import com.workintech.ecommerce.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @DisplayName("Can find Authority by name")
    @Test
    void findByAuthority() {
        // Arrange & Act
        Optional<Role> foundRole = roleRepository.findByAuthority(Enum_Role.USER);

        // Assert
        assertThat(foundRole).isPresent();
        assertThat(foundRole.get().getRole()).isEqualTo(Enum_Role.USER);
    }
}