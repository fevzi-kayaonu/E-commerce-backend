package com.workintech.ecommerce.controller;



import com.workintech.ecommerce.dto.CategoryResponseDto;
import com.workintech.ecommerce.dto.ProductResponseDto;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.service.CategoryService;
import com.workintech.ecommerce.service.OrderService;
import com.workintech.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

     private final ProductService productService;
     private final CategoryService categoryService;
     private final OrderService orderService;

     @Autowired
     public WelcomeController(ProductService productService, CategoryService categoryService, OrderService orderService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

 /*
    @GetMapping("/product")
    List<ProductResponseDto> findAllProduct(){
       return  productService.findAll();
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

    @GetMapping
    public List<ProductResponseDto> getProducts(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {
        List<Product> products = productService.getProducts(offset, count);
        return  products;
    }

  /*

    @GetMapping("/")
    List<CategoryResponseDto> findAllCategory(){
        return categoryService.findAll();
    }
 */
    //sepete ürün ekleme (id)


    //ürün search etme (ismine göre)
}