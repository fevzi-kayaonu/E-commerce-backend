package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Enum_Category;

public record CategoryResponseDto(Long id,
                                  Enum_Category name,
                                  String description) {

}
