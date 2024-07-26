package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.*;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.mapper.CategoryMapper;
import com.workintech.ecommerce.mapper.ProductMapper;
import com.workintech.ecommerce.mapper.UserMapper;
import com.workintech.ecommerce.service.CategoryService;
import com.workintech.ecommerce.service.ProductService;
import com.workintech.ecommerce.service.UserService;
import com.workintech.ecommerce.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public AdminController(ProductService productService, UserService userService, CategoryService categoryService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    // Ürün ekleme
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.createProduct(productRequestDto);
        return ProductMapper.productToProductResponseDto(product);
    }

    // Kullanıcı banlamak
    @PostMapping("/users/ban")
    public UserResponseDto banUser(@Valid @RequestBody UserBanRequestDto userBanRequestDto) {

        return UserMapper.userToUserResponseDto(userService.banUser(userBanRequestDto));
    }

    // Category ekleme
    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.save(CategoryMapper.categoryRequestDtoToCategory(categoryRequestDto));
        return CategoryMapper.categoryToCategoryResponseDto(category);
    }

}
