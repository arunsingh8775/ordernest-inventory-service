package com.ordernest.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StockUpdateRequest(
        @NotNull(message = "availableQuantity is required")
        @Min(value = 0, message = "availableQuantity must be greater than or equal to 0")
        Integer availableQuantity
) {
}
