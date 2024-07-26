package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Enum_Category;
import com.workintech.ecommerce.entity.Enum_Gender;
import com.workintech.ecommerce.entity.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


import java.util.List;

public record ProductRequestDto(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotNull(message = "Price cannot be null")
        @Positive(message = "Price must be positive")
        Double price,

        @Positive(message = "Stock quantity must be positive")
        Integer stockQuantity,

        @NotNull(message = "Gender cannot be null")
        Enum_Gender gender,

        @Valid
        Enum_Category category,

        @Size(min = 0, max = 10, message = "Image list must not be more than 10 ")
        List<@Valid ImageRequestDto> imageRequestDto
) {
}
