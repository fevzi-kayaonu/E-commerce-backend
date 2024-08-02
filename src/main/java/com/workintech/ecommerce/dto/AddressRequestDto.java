package com.workintech.ecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record AddressRequestDto(
        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotBlank(message = "Street cannot be blank")
        String street,

        @NotBlank(message = "Neighborhood cannot be blank")
        String neighborhood,

        @NotBlank(message = "District cannot be blank")
        String district,

        @NotBlank(message = "City cannot be blank")
        String city,

        @NotBlank(message = "Postal code  be blank")
        @Size(min = 5, max = 5, message = "Postal code must be exactly 5 digits")
        String postalCode
) {
}