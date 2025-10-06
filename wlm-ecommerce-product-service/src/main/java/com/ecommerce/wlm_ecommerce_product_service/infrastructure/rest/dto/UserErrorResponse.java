package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.dto;

import java.time.LocalDateTime;

public record UserErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp
){}