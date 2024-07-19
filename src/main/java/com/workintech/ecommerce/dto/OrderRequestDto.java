package com.workintech.ecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


import java.util.List;

public record OrderRequestDto(
        @Valid
        AddressRequestDto addressRequestDto,
        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")//Validasyon yazılacak
        Double amount,
        @Valid
        PaymentRequestDto paymentRequestDto,
        @Valid
        UserRequestDto userRequestDto,
        @Size(min = 1, message = "Product list must contain at least one product")
        List<@Valid ProductRequestDto> productRequestDtos
) {
}
