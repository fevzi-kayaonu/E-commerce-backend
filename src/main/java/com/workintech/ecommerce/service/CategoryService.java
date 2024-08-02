package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Enum_Category;

public interface CategoryService extends Service<Category> {
    Category getByName(Enum_Category name);
}
