package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;

    public Product(String sku, String name, String description, BigDecimal price, int quantity) {
        validate(sku,name,price,quantity);

        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    private void validate(String sku, String name, BigDecimal price, int quantity) {
        if(sku == null || sku.isBlank()){
            throw new IllegalArgumentException("invalid sku was provided");
        }
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("invalid name was provided");
        }
        if(price == null || price.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("invalid price was provided");
        }
        if(quantity <= 0){
            throw new IllegalArgumentException("Need to provide a valid quantity");
        }
    }

    public void changeName(String name){
        if(name.trim().isBlank()){
            throw new IllegalArgumentException("Name cannot blank");
        }
        this.name = name;
    }

    public void changePrice(BigDecimal price){
        if(price.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("invalid price.");
        }
        this.price = price;
    }

    public void changeQuantity(int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("invalid quantity.");
        }
        this.quantity = quantity;
    }

}
