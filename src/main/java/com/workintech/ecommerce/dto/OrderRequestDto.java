package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Enum_OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.List;

public record OrderRequestDto(
        @Valid
        Long addressId,

        @NotNull(message = "Order status name cannot be null")
        Enum_OrderStatus status,

        @Valid
        PaymentRequestDto paymentRequestDto,
        @Size(min = 1, message = "Product list must contain at least one product")
        List<Long> productIdList
) {
}
