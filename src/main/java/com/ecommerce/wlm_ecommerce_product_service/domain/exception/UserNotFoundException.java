package com.ecommerce.wlm_ecommerce_product_service.domain.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("Unable to perform this search.");
    }

    public UserNotFoundException(Long id) {
        super("User with id: " + id + " not found");
    }

    public UserNotFoundException(String msg) {
        super("User was not found");
    }
}
