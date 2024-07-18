package com.workintech.ecommerce.dto;

import jakarta.validation.constraints.Pattern;

public record ImageRequestDto(
        @Pattern(regexp = "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?$",
                message = "Invalid URL format")
        String url) {
}
