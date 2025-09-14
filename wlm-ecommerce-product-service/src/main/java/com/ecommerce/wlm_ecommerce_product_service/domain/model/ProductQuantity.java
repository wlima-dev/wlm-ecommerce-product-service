package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.Getter;

@Getter
public class ProductQuantity {
    private final int quantity;

    public ProductQuantity(int value) {
        if(value <= 0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = value;
    }

    public ProductQuantity increase(int value){
        if(value <= 0){
            throw new IllegalArgumentException("Zero or less cant be added");
        }
        return new ProductQuantity(this.quantity + value);
    }

    public ProductQuantity decrease(int value){
        if(this.quantity - value < 0){
            throw new IllegalArgumentException("Insufficient quantity");
        }
        return new ProductQuantity(this.quantity - value);
    }
}
