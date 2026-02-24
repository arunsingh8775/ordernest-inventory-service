package com.ordernest.inventory.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        BigDecimal price,
        String currency,
        Integer availableQuantity,
        String description
) {
}
