package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.ProductRequestDto;
import com.workintech.ecommerce.entity.Enum_Category;
import com.workintech.ecommerce.entity.Enum_Gender;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.repository.CategoryRepository;
import com.workintech.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository; // Ensure you have a CategoryRepository for category operations

    private Product product1;
    private Product product2;


    @BeforeEach
    void setUp() {

        ProductRequestDto productRequestDto1 = new ProductRequestDto("newBalance","ortopedik",29.99,50,Enum_Gender.UNISEX,Enum_Category.AYAKKABI,new ArrayList<>());
        ProductRequestDto productRequestDto2 = new ProductRequestDto("nike","ortopedik",29.99,50,Enum_Gender.UNISEX,Enum_Category.AYAKKABI,new ArrayList<>());

       product1 = productService.createProduct(productRequestDto1);
       product2 = productService.createProduct(productRequestDto2);
    }

    @AfterEach
    void tearDown() {

        // Testten önce eklenen ürünleri sil
        if (product1 != null) {
            productService.delete(product1.getId());
        }
        if (product2 != null) {
            productService.delete(product2.getId());
        }

    }


    @DisplayName("Ürün adı ile ürün bulunabiliyor")
    @Test
    void getByName() {
        Optional<List<Product>> foundProducts = productRepository.getByName("newBalance");

        assertTrue(foundProducts.isPresent());
        assertEquals(1, foundProducts.get().size());
        assertEquals("newBalance", foundProducts.get().get(0).getName());
    }

    @DisplayName("Kategori adı ile ürünler bulunabiliyor")
    @Test
    void getByCategory() {
        Optional<List<Product>> foundProducts = productRepository.getByCategory(Enum_Category.AYAKKABI);

        assertTrue(foundProducts.isPresent());
        assertEquals(2, foundProducts.get().size());
    }

    @DisplayName("Kategori adı ve cinsiyet ile ürünler bulunabiliyor")
    @Test
    void getByCategoryAndGender() {
        Pageable pageable = PageRequest.of(0, 10);
        Optional<Page<Product>> foundProducts = productRepository.getByCategoryAndGender(Enum_Category.AYAKKABI, Enum_Gender.UNISEX, pageable);

        assertTrue(foundProducts.isPresent());
        assertEquals(2, foundProducts.get().getTotalElements());
    }
}
