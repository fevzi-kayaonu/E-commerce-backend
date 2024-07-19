package com.workintech.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterRequestDto(@NotBlank(message = "First name cannot be blank")
                                     @Size(min = 2, max = 45, message = "First name must be between 2 and 45 characters long")
                                     String firstName,

                                     @NotBlank(message = "Last name cannot be blank")
                                     @Size(min = 2, max = 45, message = "Last name must be between 2 and 45 characters long")
                                     String lastName,

                                     @NotBlank(message = "Email cannot be blank")
                                     @Email(message = "Email should be valid")
                                     @Size(max = 45, message = "Email must be at most 45 characters long")
                                     String email,

                                     @NotBlank(message = "Password cannot be blank")
                                     @Size(min = 6, message = "Password must be at least 6 characters long")
                                     String password) {
}
