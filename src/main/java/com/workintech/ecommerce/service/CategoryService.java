package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryService extends Service<Category>{
    Category getByName(String name);
}
