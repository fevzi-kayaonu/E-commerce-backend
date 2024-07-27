package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Enum_Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getByName() {
        Optional<Category> foundCategory = categoryRepository.getByName(Enum_Category.AYAKKABI);
        assertTrue(foundCategory.isPresent());
        assertEquals(Enum_Category.AYAKKABI, foundCategory.get().getName());
    }
}