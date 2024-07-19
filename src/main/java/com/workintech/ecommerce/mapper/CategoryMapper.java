package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.CategoryRequestDto;
import com.workintech.ecommerce.dto.CategoryResponseDto;
import com.workintech.ecommerce.entity.Category;

public class CategoryMapper {
    public static Category categoryRequestDtoToCategory(CategoryRequestDto categoryRequestDto){
        Category category = new Category();
        category.setName(categoryRequestDto.name());
        category.setDescription(categoryRequestDto.description());

        return category;
    }

    public static CategoryResponseDto categoryToCategoryResponseDto (Category category){
        return new CategoryResponseDto(category.getId(),category.getName(),category.getDescription());
    }
}
