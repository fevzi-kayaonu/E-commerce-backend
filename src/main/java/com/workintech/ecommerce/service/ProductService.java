package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Product;

import java.util.List;

public interface ProductService extends Service<Product>{


    List<Product> getPriceDesc();
    List<Product> getPriceAsc();
    List<Product> getByName(String name);
    List<Product> getByCategory(String name);

}
