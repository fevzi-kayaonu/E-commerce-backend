package com.workintech.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(@NotBlank(message = "Email cannot be blank")
                             @Email(message = "Email should be valid")
                             @Size(max = 45, message = "Email must be at most 45 characters long")
                             String email) {
}
