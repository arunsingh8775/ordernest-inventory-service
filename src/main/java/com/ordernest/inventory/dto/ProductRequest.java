package com.ordernest.inventory.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "name is required")
        String name,

        @NotNull(message = "price is required")
        @DecimalMin(value = "0.0", inclusive = true, message = "price must be greater than or equal to 0")
        BigDecimal price,

        @NotBlank(message = "currency is required")
        String currency,

        @NotNull(message = "availableQuantity is required")
        @Min(value = 0, message = "availableQuantity must be greater than or equal to 0")
        Integer availableQuantity,

        @NotBlank(message = "description is required")
        String description
) {
}
