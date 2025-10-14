package com.ecommerce.wlm_ecommerce_product_service.domain.exception;

public class OrderCouldNotDeleteException extends RuntimeException {
    public OrderCouldNotDeleteException(String message) {
        super(message);
    }
}
