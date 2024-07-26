package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Enum_Category;
import jakarta.validation.constraints.NotNull;


public record CategoryRequestDto( @NotNull(message = "Category name cannot be null")
                                 Enum_Category name,
                                 @NotNull(message = "Category information cannot be null")
                                 String description) {
}
