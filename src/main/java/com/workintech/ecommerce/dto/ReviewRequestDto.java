package com.workintech.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReviewRequestDto(
        @NotBlank(message = "Street cannot be blank")
        @Size(min = 5, max = 50, message = "message must be between 5 and 50 characters")
        String message) {
}
