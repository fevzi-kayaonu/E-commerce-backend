package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Enum_Category;
import jakarta.validation.constraints.NotBlank;

public record CategoryResponseDto(Long id,
                          Enum_Category name,
                          String description) {

}
