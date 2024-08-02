package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.*;
import com.workintech.ecommerce.entity.Address;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.fetchService.DataFetchService;
import com.workintech.ecommerce.mapper.AddressMapper;
import com.workintech.ecommerce.mapper.CategoryMapper;
import com.workintech.ecommerce.mapper.ProductMapper;
import com.workintech.ecommerce.mapper.UserMapper;
import com.workintech.ecommerce.service.AddressService;
import com.workintech.ecommerce.service.CategoryService;
import com.workintech.ecommerce.service.ProductService;
import com.workintech.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final AddressService addressService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final DataFetchService dataFetchService;

    @Autowired
    public AdminController(ProductService productService, AddressService addressService, UserService userService, CategoryService categoryService, DataFetchService dataFetchService) {
        this.productService = productService;
        this.addressService = addressService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.dataFetchService = dataFetchService;
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.createProduct(productRequestDto);
        return ProductMapper.productToProductResponseDto(product);
    }

    @PostMapping("/users/ban")
    public UserResponseDto banUser(@Valid @RequestBody UserBanRequestDto userBanRequestDto) {

        return UserMapper.userToUserResponseDto(userService.banUser(userBanRequestDto));
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.save(CategoryMapper.categoryRequestDtoToCategory(categoryRequestDto));
        return CategoryMapper.categoryToCategoryResponseDto(category);
    }

    @DeleteMapping("/product/{id}")
    public ProductResponseDto removeProduct(@PathVariable Long id) {
        Product product = productService.delete(id);
        return ProductMapper.productToProductResponseDto(product);
    }

    @GetMapping("/download-data")
    public void fetchProducts() {
        for (int i = 0; i < 11; i++)
            dataFetchService.createAndSaveProducts(i * 50, 10);
    }

    @GetMapping("/address")
    List<AddressResponseDto> findAllProduct() {
        List<Address> addresses = addressService.findAll();
        return addresses.stream().map(AddressMapper::addressToAddressResponseDto).toList();
    }
}
