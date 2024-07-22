package com.workintech.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

public record UserBanRequestDto(
        @NotNull(message = "User ID cannot be null") Long userId,
        String reason) {
}