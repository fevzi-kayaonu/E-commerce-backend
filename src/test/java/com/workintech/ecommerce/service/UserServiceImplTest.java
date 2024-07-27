package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserServiceImplTest {

    private final UserRepository userRepository;
    private final RegisterService registerService;

    @Autowired
    public UserServiceImplTest(UserRepository userRepository, RegisterService registerService) {
        this.userRepository = userRepository;
        this.registerService = registerService;
    }

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = registerService.register("John", "Doe", "johndoe@example.com", "password");
    }

    @AfterEach
    void tearDown() {
        if (testUser != null) {
            userRepository.delete(testUser);
        }
    }

    @DisplayName("Kullanıcı e-posta ile bulunabiliyor")
    @Test
    void findByEmail() {
        // findByEmail metodunu test et
        Optional<User> foundUser = userRepository.findByEmail("johndoe@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("johndoe@example.com", foundUser.get().getEmail());
        assertEquals("John", foundUser.get().getFirstName());
        assertEquals("Doe", foundUser.get().getLastName());
    }
}
