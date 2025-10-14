package com.ecommerce.wlm_ecommerce_product_service.domain.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("Product with id: " + id + " not found");
    }

    public ProductNotFoundException(String msg){
        super(msg);
    }
}
