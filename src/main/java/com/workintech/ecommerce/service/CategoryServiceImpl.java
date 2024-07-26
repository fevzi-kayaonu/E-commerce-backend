package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Category;

import com.workintech.ecommerce.entity.Enum_Category;
import com.workintech.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements  CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(null) ;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
        return category;
    }

    @Override
    public Category getByName(Enum_Category name) {
        return categoryRepository.getByName(name);
    }
}
