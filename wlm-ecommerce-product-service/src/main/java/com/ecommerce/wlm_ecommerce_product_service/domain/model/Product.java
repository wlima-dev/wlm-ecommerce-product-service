package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Product {

    private final String SKU;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int quantity;

    public Product(String SKU, String name, String description, BigDecimal price, int quantity) {
        if(SKU == null || SKU.isBlank()){
            throw new IllegalArgumentException("SKU cannot be null or blank");
        }
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name is null or blank");
        }
        if(price == null || price.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("price cannot be null or blank");
        }

        if(price == null || price.compareTo(BigDecimal.ZERO) < 0 && quantity <= 0){
            throw new IllegalArgumentException("price cannot be null or blank");
        }

        this.SKU = SKU;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public Product changeName(String name){
        return new Product(
                this.SKU,
                name,
                this.description,
                this.price,
                this.quantity);
    }

    public Product changeDescription(String description){
        return new Product(
                this.SKU,
                this.name,
                description,
                this.price,
                this.quantity);
    }

    public Product changePrice(BigDecimal newPrice){
        return new Product(
                this.SKU,
                this.name,
                this.description,
                newPrice,
                this.quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "SKU='" + SKU + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", productQuantity=" + quantity +
                '}';
    }
}
