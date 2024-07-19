package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Enum_Gender;

import java.time.Instant;
import java.util.List;

public record ProductResponseDto(
        Long id,

        String name,

        String description,

        Double price,

        Double rating,

        Integer stockQuantity,

        Enum_Gender gender,

        Instant createdAt,
        CategoryResponseDto categoryResponseDto,
        List<ImageResponseDto> imageResponseDtos
) {
}
