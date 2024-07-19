package com.workintech.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressResponseDto(Long id,
                                 String description,
                                 String street,
                                 String neighborhood,
                                 String district,
                                 String city,
                                 String postalCode) {

}
