package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Product;
import org.hibernate.query.Page;

import java.util.List;

public interface ProductService extends Service<Product>{


    List<Product> getPriceDesc();
    List<Product> getPriceAsc();
    List<Product> getByName(String name);
    List<Product> getByCategory(String name);
   // Page<Product> getByCategoryAndGender(String name, String gender);
    List<Product> getProducts(int offset, int count);

}
