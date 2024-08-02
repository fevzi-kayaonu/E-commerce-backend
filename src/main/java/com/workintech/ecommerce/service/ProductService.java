package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.ProductRequestDto;
import com.workintech.ecommerce.entity.Enum_Category;
import com.workintech.ecommerce.entity.Enum_Gender;
import com.workintech.ecommerce.entity.Product;

import java.util.List;

public interface ProductService extends Service<Product> {
    List<Product> getPriceDesc();

    List<Product> getPriceAsc();

    List<Product> getByName(String name);

    List<Product> getByCategory(Enum_Category name);

    List<Product> getByCategoryAndGender(Enum_Category name, Enum_Gender gender, int offset, int count);

    List<Product> getProducts(int offset, int count);

    Product createProduct(ProductRequestDto productRequestDto);

}
