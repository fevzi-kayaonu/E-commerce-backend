package com.workintech.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.ecommerce.dto.ProductResponseDto;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Enum_Category;
import com.workintech.ecommerce.entity.Enum_Gender;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.service.ProductService;
import com.workintech.ecommerce.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(WelcomeController.class)
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {

        product1 = new Product();
        product1.setId(1L);
        product1.setName("newBalance");
        product1.setCategory(new Category());
        product1.setGender(Enum_Gender.UNISEX);

        product2 = new Product();
        product2.setId(2L);
        product2.setName("nike");
        product2.setCategory(new Category());
        product2.setGender(Enum_Gender.UNISEX);

        List<Product> products = Arrays.asList(product1, product2);
        List<ProductResponseDto> dtos = products.stream()
                .map(ProductMapper::productToProductResponseDto)
                .toList();

        when(productService.getProducts(0, 10)).thenReturn(products);
        when(productService.getByCategoryAndGender(Enum_Category.AYAKKABI, Enum_Gender.UNISEX, 0, 10)).thenReturn(products);
    }

    @Test
    void getProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/welcome/")
                        .param("offset", "0")
                        .param("count", "10")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("password").roles("USER")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(
                        Arrays.asList(ProductMapper.productToProductResponseDto(product1), ProductMapper.productToProductResponseDto(product2))
                )));
    }

    @Test
    void getByCategoryAndGender() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/welcome/category/gender")
                        .param("name", Enum_Category.AYAKKABI.name())
                        .param("gender", Enum_Gender.UNISEX.name())
                        .param("offset", "0")
                        .param("count", "10")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("password").roles("USER")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(
                        Arrays.asList(ProductMapper.productToProductResponseDto(product1), ProductMapper.productToProductResponseDto(product2))
                )));
    }
}