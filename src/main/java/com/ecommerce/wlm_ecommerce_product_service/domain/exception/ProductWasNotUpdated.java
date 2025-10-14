package com.ecommerce.wlm_ecommerce_product_service.domain.exception;

public class ProductWasNotUpdated extends RuntimeException {
    public ProductWasNotUpdated(String message) {
        super(message);
    }
}
