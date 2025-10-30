package com.ecommerce.wlm_ecommerce_product_service.domain.exception;

public class OrderAlreadyPaidException extends Exception {
    public OrderAlreadyPaidException(String message) {
        super(message);
    }
}
