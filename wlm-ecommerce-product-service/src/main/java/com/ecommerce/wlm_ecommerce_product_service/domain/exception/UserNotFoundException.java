package com.ecommerce.wlm_ecommerce_product_service.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Unable to perform this search.");
    }

    public UserNotFoundException(Long id) {
        super("User with id: " + id + " not found");
    }
}
