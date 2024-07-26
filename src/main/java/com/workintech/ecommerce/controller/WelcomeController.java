package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.ProductResponseDto;
import com.workintech.ecommerce.entity.Enum_Category;
import com.workintech.ecommerce.entity.Enum_Gender;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.mapper.ProductMapper;
import com.workintech.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

     private final ProductService productService;


     @Autowired
     public WelcomeController(ProductService productService) {
        this.productService = productService;
    }

/*
    @GetMapping("/product")
    List<ProductResponseDto> findAllProduct(){
        List<Product> products = productService.findAll();
        return products.stream().map(ProductMapper::productToProductResponseDto).toList();
    }
    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable long id){
        return productService.findById(id);
    }

    @GetMapping("/category")
    List<CategoryResponseDto> findAllCategory(){
       return categoryService.findAll();
    }

    @GetMapping("/order")
    List<CategoryResponseDto> findAllOrder(){
        return categoryService.findAll();
    }
  */

    @GetMapping("/")
    public List<ProductResponseDto> getProducts(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {
        List<Product> products = productService.getProducts(offset, count);
        return products.stream().map(ProductMapper::productToProductResponseDto).toList();
    }

    @GetMapping("/category/gender")
    List<ProductResponseDto> getByCategoryAndGender(@RequestParam Enum_Category name,
                                          @RequestParam Enum_Gender gender,
                                          @RequestParam(defaultValue = "0")  int offset,
                                          @RequestParam(defaultValue = "10")  int count){

        List<Product> products = productService.getByCategoryAndGender(name,gender,offset,count);
        return products.stream().map(ProductMapper::productToProductResponseDto).toList();
    }

  /*

    @GetMapping("/")
    List<CategoryResponseDto> findAllCategory(){
        return categoryService.findAll();
    }
 */


    //ürün search etme (ismine göre)
}
